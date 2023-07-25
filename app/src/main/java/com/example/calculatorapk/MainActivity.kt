package com.example.calculatorapk

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity(), FragmentActionListener,
    FragmentManager.OnBackStackChangedListener {

    private var currentConfiguration = 0
    private var onBtnSelected1: String = ""
    private lateinit var fragmentManager: FragmentManager
    private lateinit var firstFragment: FirstFragment
    private val bundleKey = "BundleKey"
    override fun onCreate(savedInstanceState: Bundle?) {

        fragmentManager = supportFragmentManager
        currentConfiguration = resources.configuration.orientation

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) showDefaultFragment()

        val check = savedInstanceState?.getString(bundleKey)
        if (!check.isNullOrEmpty()) {

            onBtnSelected1 = check
            if (currentConfiguration == Configuration.ORIENTATION_PORTRAIT) {
                addSecondFragment(onBtnSelected1)
                val frgOne = supportFragmentManager.findFragmentById(R.id.fragmentContainer2)
                if (frgOne != null) {
                    supportFragmentManager.beginTransaction().remove(frgOne).commit()
                }

            } else {
                addSecondFragment(R.id.fragmentContainer2, onBtnSelected1)
            }
        }
//        onBackPressedDispatcher.onBackPressed()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPress()
            }

        })

    }

    private fun showDefaultFragment() {
        firstFragment = FirstFragment()
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, firstFragment, "firstFragment").commit()
        }
    }

    override fun passBtnSelectedText(onBtnSelected: String) {
        if (currentConfiguration == Configuration.ORIENTATION_PORTRAIT) addSecondFragment(
            onBtnSelected
        )
        else if (currentConfiguration == Configuration.ORIENTATION_LANDSCAPE) {
            addSecondFragment(R.id.fragmentContainer2, onBtnSelected)
        }

    }

    private fun addSecondFragment(onBtnSelected: String) {
        onBtnSelected1 = onBtnSelected

        val bundle = Bundle()
        bundle.putString(Constants.btnText, onBtnSelected)

        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, secondFragment, "secondFragment")
            addToBackStack(null)
            commit()
        }
    }

    private fun addSecondFragment(containerId: Int, onBtnSelected: String) {
        onBtnSelected1 = onBtnSelected

        val bundle = Bundle()
        bundle.putString(Constants.btnText, onBtnSelected)

        if (fragmentManager.backStackEntryCount >= 0) {
            supportFragmentManager.popBackStackImmediate(
                null, FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        fragmentManager.beginTransaction().apply {
            replace(containerId, secondFragment, "secondFragment")
            addToBackStack(null)
            commit()
        }
    }

    override fun passBackAnsText(ansText: String) {
        val fragment = fragmentManager.findFragmentByTag("firstFragment") as FirstFragment

        onBtnSelected1 = ""

        val bundle = Bundle()
        bundle.putString(Constants.ansTxt, ansText)
        fragment.arguments = bundle

        if (Configuration.ORIENTATION_LANDSCAPE == currentConfiguration) fragment.showResultView()

    }


    fun onBackPress() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (onBtnSelected1.isNotEmpty()) {
            Toast.makeText(this, " onSaved $onBtnSelected1", Toast.LENGTH_SHORT).show()
            outState.putString(bundleKey, onBtnSelected1)
        }

    }


    override fun onBackStackChanged() {
        for (i in 0..supportFragmentManager.backStackEntryCount) {
            Log.d("BackStack entry", "${supportFragmentManager.getBackStackEntryAt(i).name}")
        }
    }


}
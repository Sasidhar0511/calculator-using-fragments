package com.example.calculatorapk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class FirstFragment : Fragment(),View.OnClickListener {

    private lateinit var addButton : Button
    private lateinit var subButton : Button
    private lateinit var mulButton : Button
    private lateinit var divButton : Button
    private lateinit var resetButton : Button
    private lateinit var tvResult : TextView

    private lateinit var fragmentActionListener: FragmentActionListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentActionListener = activity as FragmentActionListener

        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        addButton = view.findViewById(R.id.btnAdd)
        subButton = view.findViewById(R.id.btnSub)
        mulButton = view.findViewById(R.id.btnMul)
        divButton = view.findViewById(R.id.btnDiv)
        resetButton = view.findViewById(R.id.btnReset)
        tvResult = view.findViewById(R.id.tvResult)

        addButton.setOnClickListener (this)
        subButton.setOnClickListener (this)
        mulButton.setOnClickListener (this)
        divButton.setOnClickListener (this)

        if(arguments!=null){
            showResultView()
        }

        resetButton.setOnClickListener {

            addButton.visibility = View.VISIBLE
            subButton.visibility = View.VISIBLE
            mulButton.visibility = View.VISIBLE
            divButton.visibility = View.VISIBLE

            resetButton.visibility = View.GONE
            tvResult.visibility = View.GONE

            tvResult.text = null
            arguments = null
        }

        return view
    }

    fun showResultView() {

        addButton.visibility = View.GONE
        subButton.visibility = View.GONE
        mulButton.visibility = View.GONE
        divButton.visibility = View.GONE
        resetButton.visibility = View.VISIBLE
        tvResult.visibility = View.VISIBLE

        val result = arguments?.getString(Constants.ansTxt)
        tvResult.text = result
    }

    override fun onClick(view: View?) {
        lateinit var btnText: String

        when (view?.id) {
            R.id.btnAdd -> { btnText = Constants.add }
            R.id.btnSub -> { btnText = Constants.sub }
            R.id.btnMul -> { btnText = Constants.mul }
            R.id.btnDiv -> { btnText = Constants.div }
        }

        fragmentActionListener.passBtnSelectedText(btnText)

    }


}
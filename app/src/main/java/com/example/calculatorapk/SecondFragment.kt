package com.example.calculatorapk

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast



class SecondFragment : Fragment() {

    private lateinit var fragmentActionListener: FragmentActionListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentActionListener = activity as FragmentActionListener

        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        var ans : Int? = null
        var ansText  = ""
        val firstNum = view.findViewById<EditText>(R.id.etFirstNo)
        val secondNum = view.findViewById<EditText>(R.id.etSecondNo)
        val btn = view.findViewById<Button>(R.id.button)

        val btnText = arguments?.getString(Constants.btnText)
        btn.text = btnText

        btn.setOnClickListener {

            val firstNo = firstNum.text.toString().toInt()
            val secNo = secondNum.text.toString().toInt()

            when (btnText) {
                Constants.add -> { ans = firstNo + secNo; ansText = getString(R.string.AddText, firstNo, secNo, ans) }
                Constants.sub -> { ans = firstNo - secNo; ansText = getString(R.string.SubText, firstNo, secNo, ans) }
                Constants.mul -> { ans = firstNo * secNo; ansText = getString(R.string.MulText, firstNo, secNo, ans) }
                Constants.div -> {
                    if (secNo == 0) {
                        Toast.makeText(context, R.string.DivideByZeroError, Toast.LENGTH_SHORT).show()
                    } else {
                        ans = firstNo / secNo
                        ansText = getString(R.string.DivText, firstNo, secNo, ans)
                    }
                }
            }

            if (ans != null ) {
                parentFragmentManager.popBackStack()
                fragmentActionListener.passCallBackData(ansText)
            }

        }

        return view
    }


}
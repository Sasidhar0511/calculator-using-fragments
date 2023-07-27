package com.example.calculatorapk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.NumberFormatException


class SecondFragment : Fragment() {

    private lateinit var fragmentActionListener: FragmentActionListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentActionListener = activity as FragmentActionListener

        val view = inflater.inflate(R.layout.fragment_second, container, false)

        var answer: Int? = null
        var ansText = ""
        val firstNum = view.findViewById<EditText>(R.id.etFirstNo)
        val secondNum = view.findViewById<EditText>(R.id.etSecondNo)
        val operationBtn = view.findViewById<Button>(R.id.button)

        val btnText = arguments?.getString(Constants.btnText)
        operationBtn.text = btnText

        operationBtn.setOnClickListener {

            try {
                val firstNo = firstNum.text.toString().toInt()
                val secNo = secondNum.text.toString().toInt()

                when (btnText) {
                    Constants.add -> {
                        answer = firstNo + secNo; ansText =
                            getString(R.string.AddText, firstNo, secNo, answer)
                    }

                    Constants.sub -> {
                        answer = firstNo - secNo; ansText =
                            getString(R.string.SubText, firstNo, secNo, answer)
                    }

                    Constants.mul -> {
                        answer = firstNo * secNo; ansText =
                            getString(R.string.MulText, firstNo, secNo, answer)
                    }

                    Constants.div -> {
                        if (secNo == 0) {
                            Toast.makeText(context, R.string.DivideByZeroError, Toast.LENGTH_SHORT).show()
                        } else {
                            answer = firstNo / secNo
                            ansText = getString(R.string.DivText, firstNo, secNo, answer)
                        }
                    }
                }
            } catch (exception: NumberFormatException) {

                Toast.makeText(
                    requireActivity().baseContext,
                    R.string.NumberFormatErrorString,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }



            if (answer != null) {
                parentFragmentManager.popBackStack()
                fragmentActionListener.passBackAnsText(ansText)
            }

        }

        return view
    }


}
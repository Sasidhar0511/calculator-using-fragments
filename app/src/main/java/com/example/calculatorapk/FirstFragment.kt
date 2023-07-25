package com.example.calculatorapk

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class FirstFragment : Fragment(),View.OnClickListener {

    private lateinit var btnAdd : Button
    private lateinit var btnSub : Button
    private lateinit var btnMul : Button
    private lateinit var btnDiv : Button
    private lateinit var btnReset : Button
    private lateinit var tvRes : TextView

    private lateinit var fragmentActionListener: FragmentActionListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentActionListener = activity as FragmentActionListener

        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        btnAdd = view.findViewById(R.id.btnAdd)
        btnSub = view.findViewById(R.id.btnSub)
        btnMul = view.findViewById(R.id.btnMul)
        btnDiv = view.findViewById(R.id.btnDiv)
        btnReset = view.findViewById(R.id.btnReset)
        tvRes = view.findViewById(R.id.tvResult)

        btnAdd.setOnClickListener (this)
        btnSub.setOnClickListener (this)
        btnMul.setOnClickListener (this)
        btnDiv.setOnClickListener (this)

        if(arguments!=null){
            Toast.makeText(context,"hi from args", Toast.LENGTH_SHORT).show()
            showResultView()
        }

        btnReset.setOnClickListener {

            btnAdd.visibility = View.VISIBLE
            btnSub.visibility = View.VISIBLE
            btnMul.visibility = View.VISIBLE
            btnDiv.visibility = View.VISIBLE

            btnReset.visibility = View.GONE
            tvRes.visibility = View.GONE

            //if(tvRes.text.toString().isNotEmpty())
            tvRes.text = null
            arguments = null
        }

        return view
    }

    fun showResultView() {
       // Toast.makeText(context,"resultView",Toast.LENGTH_SHORT).show()

        btnAdd.visibility = View.GONE
        btnSub.visibility = View.GONE
        btnMul.visibility = View.GONE
        btnDiv.visibility = View.GONE
        btnReset.visibility = View.VISIBLE
        tvRes.visibility = View.VISIBLE

        val result = arguments?.getString(Constants.ansTxt)
        tvRes.text = result
    }

    override fun onClick(view: View?) {
        lateinit var btnText: String

        when (view?.id) {
            R.id.btnAdd -> { btnText = Constants.add }
            R.id.btnSub -> { btnText = Constants.sub }
            R.id.btnMul -> { btnText = Constants.mul }
            R.id.btnDiv -> { btnText = Constants.div }
        }

        fragmentActionListener.passData(btnText)

    }


}
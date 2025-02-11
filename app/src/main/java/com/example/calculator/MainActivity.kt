package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    var lastnumeric: Boolean = false
    var lastdot: Boolean =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput= findViewById(R.id.tvinput)
    }
    fun ondigit(view: View){
        tvinput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false


    }
    fun onclear(view: View){
        tvinput?.text=""
    }
    fun ondecimalpoint(view: View){
        if (lastnumeric && !lastdot){
            tvinput?.append(".")
            lastnumeric = false
            lastdot = true
        }

    }
    fun onoperator(view: View){
        tvinput?.text?.let{
            if(lastnumeric && !isoperatoradded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumeric=false
                lastdot=false

            }
        }

    }
    fun onequal(view: View){
        if(lastnumeric){
            var tvvalue = tvinput?.text.toString()
            var prefix= ""

            try {
                if (tvvalue.startsWith("-")){
                    prefix="-"
                    tvvalue= tvvalue.substring(1)
                }
                if(tvvalue.contains("-")) {

                    var splitvalue = tvvalue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvinput?.text = removezeroafterdot((one.toDouble() - two.toDouble()).toString())
                }else if(tvvalue.contains("+")) {

                    var splitvalue = tvvalue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvinput?.text = removezeroafterdot((one.toDouble() + two.toDouble()).toString())
                }else if(tvvalue.contains("/")) {

                    var splitvalue = tvvalue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvinput?.text = removezeroafterdot((one.toDouble() / two.toDouble()).toString())
                }else if(tvvalue.contains("*")) {

                    var splitvalue = tvvalue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvinput?.text = removezeroafterdot((one.toDouble() *  two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removezeroafterdot(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length -2)
        return value
    }
    private fun isoperatoradded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }
}
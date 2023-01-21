package com.example.tiptime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button  = findViewById(R.id.calculate_btn)
        button.setOnClickListener{

            calculateTip()
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun calculateTip() {

        val editText : EditText = findViewById(R.id.editText)
        val stringInTextField = editText.text.toString()
        val cost = stringInTextField.toDouble()
        val radioButton1 : RadioButton = findViewById(R.id.option_twenty_percent)
        val radioButton2 : RadioButton = findViewById(R.id.option_eighteen_percent)
        val radioButton3 : RadioButton = findViewById(R.id.option_fifteen_percent)
        val tipPercentage : Double

        if(radioButton1.isChecked)
            tipPercentage = 0.20
        else if (radioButton2.isChecked)
            tipPercentage = 0.18
        else
            tipPercentage = 0.15

        var tip = cost * tipPercentage
        val switch : Switch = findViewById(R.id.tip_switch)

        if(switch.isChecked)
            tip = kotlin.math.ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        val result_tip : TextView = findViewById(R.id.tip_result)
        result_tip.text = getString(R.string.tip_amount, formattedTip)
    }
}
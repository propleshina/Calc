package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_main)

        binding.button0.setOnClickListener { AddToInput("0") }
        binding.button1.setOnClickListener { AddToInput("1") }
        binding.button2.setOnClickListener { AddToInput("2") }
        binding.button3.setOnClickListener { AddToInput("3") }
        binding.button4.setOnClickListener { AddToInput("4") }
        binding.button5.setOnClickListener { AddToInput("5") }
        binding.button6.setOnClickListener { AddToInput("6") }
        binding.button7.setOnClickListener { AddToInput("7") }
        binding.button8.setOnClickListener { AddToInput("8") }
        binding.button9.setOnClickListener { AddToInput("9") }

        binding.buttonDot.setOnClickListener { AddToInput(".") }
        binding.buttonAddition.setOnClickListener { AddToInput("+") }
        binding.buttonSubtraction.setOnClickListener { AddToInput("-") }
        binding.buttonMultiply.setOnClickListener { AddToInput("×") }
        binding.buttonDivision.setOnClickListener { AddToInput("÷") }
        binding.buttonBracketLeft.setOnClickListener { AddToInput("(") }
        binding.buttonBracketRight.setOnClickListener { AddToInput(")") }

        binding.buttonClear.setOnClickListener {
            clearInput()
        }

        binding.buttonEquals.setOnClickListener {
            calculateResult()
        }

    }
    private fun AddToInput(value: String) {
        binding.input.text = "${binding.input.text}$value"
    }

    private fun clearInput() {
        binding.input.text = ""
        binding.output.text = ""
    }

    private fun GetInputExpression(): String {
        return binding.input.text.toString()
    }

    private fun calculateResult() {
        val expressionText = GetInputExpression()

        try {
            val expression = Expression(expressionText)
            val result = expression.calculate()

            if (result.isNaN()) {
                ShowError()
            } else {
                ShowSuccessResult(result)
            }
        } catch (e: Exception) {
            ShowError()
        }
    }

    private fun ShowError() {
        binding.output.text = "Error"
        binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun ShowSuccessResult(result: Double) {
        val decimalFormat = DecimalFormat("0.######")
        binding.output.text = decimalFormat.format(result)
        binding.output.setTextColor(ContextCompat.getColor(this, R.color.purple))
    }
}
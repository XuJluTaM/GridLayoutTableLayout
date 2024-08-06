package com.example.simpleapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextET: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        editTextET = findViewById(R.id.editTextET)
        resultTextView = findViewById(R.id.resultTextView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finish() // Закрытие приложения
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onDigitClick(view: android.view.View) {
        val button = view as android.widget.Button
        editTextET.append(button.text)
    }

    fun onOperationClick(view: android.view.View) {
        val button = view as android.widget.Button
        editTextET.append(" ${button.text} ")
    }

    fun onEqualClick(view: android.view.View) {
        try {
            val input = editTextET.text.toString()
            val result = evaluateExpression(input)
            resultTextView.text = result.toString()
        } catch (e: Exception) {
            resultTextView.text = "Ошибка"
        }
    }

    fun onResetClick(view: android.view.View) {
        editTextET.text.clear()
        resultTextView.text = ""
    }

    private fun evaluateExpression(expression: String): Double {
        // Простейший расчет без учета приоритетов операций
        val tokens = expression.split(" ")
        var result = tokens[0].toDouble()
        var i = 1
        while (i < tokens.size) {
            val operator = tokens[i]
            val value = tokens[i + 1].toDouble()
            when (operator) {
                "+" -> result += value
                "-" -> result -= value
                "*" -> result *= value
                "/" -> result /= value
            }
            i += 2
        }
        return result
    }
}

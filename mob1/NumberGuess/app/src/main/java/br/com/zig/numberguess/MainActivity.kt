package br.com.zig.numberguess

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var sortedNumber: Int = 0
    private var tries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sortedNumber = (1..100).random()
        tries = 0
        println("SORTED NUMBER --> $sortedNumber")
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun guess(view: View) {
        val input = findViewById<EditText>(R.id.editTextInput)
        val output = findViewById<TextView>(R.id.textViewOutPut)
        var tip: String = "Número sorteado é maior!!!"

        if (input.length() == 0) {
            Toast.makeText(this, "Fornaça o número!!!", Toast.LENGTH_SHORT).show()
        } else {
            val inputNumber: Int = input.text.toString().toInt()
            tries++

            if (sortedNumber < inputNumber)
                tip = "Número sorteado é menor!!!"
            else if (sortedNumber == inputNumber)
                tip = "Acertou!!!\nVocê usou $tries tentativas."
            output.text = tip
            input.text = null
        }
    }
}
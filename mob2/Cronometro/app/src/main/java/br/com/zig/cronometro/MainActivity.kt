package br.com.zig.cronometro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvTime = findViewById<TextView>(R.id.tvTime)
        btnStart = findViewById<Button>(R.id.btnStart)

        // Define a ação do botão
        btnStart.setOnClickListener {
            startCounter()
        }
    }

    //Função que inicia o cronômetro
    private fun startCounter() {
        // Inicia a corrotina no escopo do ciclo de vida (lifecycleScope)
        lifecycleScope.launch(Dispatchers.Main) {
            // Atualiza o contador de 1 até 10
            for (i in 1..10) {
                tvTime.text = i.toString() // Atualiza o textview com o valor atual
                delay(1000L) // Espera 1 segundo
            }
        }
    }
}
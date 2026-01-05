package br.com.zig.simrequestapi

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun request(view: View) {
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val progressBar = findViewById<View>(R.id.progressBar)
        tvStatus.text = "Status: Iniciando acesso a API..."
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.Main) {
            tvStatus.text = "Status: Iniciando acesso à API..."
            progressBar.visibility = View.VISIBLE
            withContext(Dispatchers.IO) {
                delay(2000)
            }
            progressBar.visibility = View.GONE
            tvStatus.text = "Status: Acesso a API realizado com sucesso!!!"
        }
    }
}
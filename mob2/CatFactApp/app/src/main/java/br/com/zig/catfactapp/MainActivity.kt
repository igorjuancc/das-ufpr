package br.com.zig.catfactapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var catFactApi: CatFactApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        // Configurar o Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catFactApi = retrofit.create(CatFactApi::class.java)
    }

    fun getFact(view: View) {
        // Mostra progress bar
        progressBar.visibility = View.VISIBLE
        tvResult.visibility = View.GONE

        // Inicia uma coroutine para fazer a requisição em segundo plano
        lifecycleScope.launch {
            try {
                // Faz a requisição em segundo plano
                val factResponse = withContext(Dispatchers.IO) {
                    catFactApi.getCatFact()
                }
                // Mata progress bar
                progressBar.visibility = View.GONE
                tvResult.visibility = View.VISIBLE
                // Atualiza o TextView com o resultado
                tvResult.text = factResponse.fact
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao buscar o fato", e)
                tvResult.text = "Erro ao buscar o fato"
            }
        }
    }
}
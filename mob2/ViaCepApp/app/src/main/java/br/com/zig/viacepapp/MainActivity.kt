package br.com.zig.viacepapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var etCep: EditText
    private lateinit var btnGetAddress: Button
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var viaCepApi: ViaCepApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etCep = findViewById(R.id.etCep)
        btnGetAddress = findViewById(R.id.btnGetAddress)
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        // Configurar o Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viaCepApi = retrofit.create(ViaCepApi::class.java)
    }

    fun getAddress(view: View) {
        val cep = etCep.text.toString()

        if(cep.isNotEmpty()) {
            getAddressFromApi(cep)
        } else {
            tvResult.text = "Por favor, insira um CEP válido."
        }
    }

    private fun showLoading() {
        progressBar.visibility = ProgressBar.VISIBLE
        tvResult.visibility = TextView.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = ProgressBar.GONE
        tvResult.visibility = TextView.VISIBLE
    }

    // Função para buscar o CEP usando corrotinas
    private fun getAddressFromApi(cep: String) {
        lifecycleScope.launch {
            try {
                showLoading()
                // Faz a requisição no contexto do IO (input/output)
                val endereco = withContext(Dispatchers.IO) {
                    viaCepApi.getAddress(cep)
                }
                // Exibe o resultado no TextView
                tvResult.text = """
                    CEP: ${endereco.cep}
                    Logradouro: ${endereco.street}
                    Complemento: ${endereco.number}
                    Cidade: ${endereco.city}
                    Estado: ${endereco.state}
                """.trimIndent()
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao buscar o CEP", e)
                tvResult.text = "Erro ao buscar o CEP. Verifique o CEP inserido."
            } finally {
                hideLoading()
            }
        }
    }
}
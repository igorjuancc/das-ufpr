package br.com.zig.dogimageapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    
    private lateinit var erBreed: EditText
    private lateinit var btnGetImage: Button
    private lateinit var ivDog: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var dogApi: DogApi
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        erBreed = findViewById(R.id.etBreed)
        btnGetImage = findViewById(R.id.btnGetImage)
        ivDog = findViewById(R.id.ivDog)
        progressBar = findViewById(R.id.progressBar)

        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dogApi = retrofit.create(DogApi::class.java)
    }

    fun getImage(view: View) {
        val breed = erBreed.text.toString().lowercase()

        if (breed.isNotEmpty()) {
            getImageApi(breed)
        } else {
            Toast.makeText(this, "Por favor, insira a raça do cachorro",
                Toast.LENGTH_SHORT).show()
        }
    }

    // Função para buscar a imagem do cachorro usando corrotinas
    private fun getImageApi(breed: String) {
        lifecycleScope.launch {
            try {
                showProgressBar()
                // Faz a requisição em segundo plano
                val response = withContext(Dispatchers.IO) {
                    dogApi.getRandomDogImage(breed)
                }
                hideProgressBar()
                // Verifica se a resposta foi bem-sucedida
                if(response.status == "success") {
                    // Carrega a imagem no ImageView usando Picasso
                    Picasso.get().load(response.message).into(ivDog)
                } else {
                    Toast.makeText(this@MainActivity, "Raça não encontrada",
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao buscar imagem", e)
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao buscar imagem. Tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Função para mostrar o ProgressBar
    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        ivDog.visibility = View.GONE
    }

    // Função para esconder o ProgressBar
    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        ivDog.visibility = View.VISIBLE
    }
}
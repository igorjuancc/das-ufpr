package br.com.zig.placeholderpost

import android.os.Bundle
import android.util.Log
import android.view.View
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

    private lateinit var textViewResult: TextView
    private lateinit var postApi: PostApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)

        // Configura Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        postApi = retrofit.create(PostApi::class.java)
    }

    fun doIt(view: View) {
        when(view.id) {
            R.id.buttonGet -> getPost((1..100).random())
            R.id.buttonPost -> createPost(Post(userId = 1, title = "Novo Post",
                body = "Este é um novo post."))
            R.id.buttonPut -> updatePost(1, Post(userId = 1,
                title = "Post Atualizado", body = "Este post foi atualizado."))
            R.id.buttonDelete -> deletePost(1)
        }
    }

    // Função para buscar um post usando GET
    private fun getPost(postId: Int) {
        lifecycleScope.launch {
            try {
                val post = withContext(Dispatchers.IO) {
                    postApi.getPost(postId)
                }

                textViewResult.text = "ID: ${post.id}\n\n" +
                        "Titulo: ${post.title}\n\n" +
                        "Corpo: ${post.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao buscar o post", e)
                textViewResult.text = "Erro ao buscar o post."
            }
        }
    }

    // Função para criar um novo post usando POST
    private fun createPost(newPost: Post) {
        lifecycleScope.launch {
            try {
                val createdPost = withContext(Dispatchers.IO) {
                    postApi.createPost(newPost)
                }

                textViewResult.text = "Post criado com sucesso!\n\n" +
                        "ID: ${createdPost.id}\n\n" +
                        "Titulo: ${createdPost.title}\n\n" +
                        "Corpo: ${createdPost.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao criar o post", e)
                textViewResult.text = "Erro ao criar o post."
            }
        }
    }

    // Função para atualizar um post usando PUT
    private fun updatePost(postId: Int, updatePost: Post) {
        lifecycleScope.launch {
            try {
                val updated = withContext(Dispatchers.IO) {
                    postApi.updatePost(postId, updatePost)
                }

                textViewResult.text = "Post atualizado com sucesso!\n\n" +
                        "ID: ${updated.id}\n\n" +
                        "Titulo: ${updated.title}\n\n" +
                        "Corpo: ${updated.body}"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao atualizar o post", e)
                textViewResult.text = "Erro ao atualizar o post."
            }
        }
    }

    // Função para deletar um post usando DELETE
    private fun deletePost(postId: Int) {
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    postApi.deletePost(postId)
                }

                textViewResult.text = "Post ${postId} deletado com sucesso!"
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao deletar o post", e)
                textViewResult.text = "Erro ao deletar o post."
            }
        }
    }
}
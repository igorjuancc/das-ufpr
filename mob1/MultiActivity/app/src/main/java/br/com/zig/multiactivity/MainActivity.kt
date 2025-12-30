package br.com.zig.multiactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

    fun goNextActivity(view: View) {
        val intent = Intent(this, DetailActivity::class.java)
        /*
        intent.putExtra("userName", "Igor")
        intent.putExtra("n1Peso", 2)
        intent.putExtra("n1", 9.2)
        */
        val user = User (
            userName = "Igor",
            n1Peso = 2,
            n1 = 8.5
        )
        intent.putExtra("user", user)
        startActivity(intent)
    }
}
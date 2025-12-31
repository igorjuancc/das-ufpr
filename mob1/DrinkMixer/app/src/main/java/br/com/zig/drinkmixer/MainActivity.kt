package br.com.zig.drinkmixer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val drinks = arrayOf("Batida de Sonho de Valsa", "Drink dos Deuses", "Quentão")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, drinks)

        listView.adapter =  arrayAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            goDetailsActivity(position)
        }
    }

    fun goDetailsActivity(drinkIndex: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("drinkIndex", drinkIndex)
        startActivity(intent)
    }
}
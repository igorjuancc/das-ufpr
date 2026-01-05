package br.com.zig.harrypotterdbapp.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.zig.harrypotterdbapp.R
import br.com.zig.harrypotterdbapp.data.dao.HPCharDAO
import br.com.zig.harrypotterdbapp.model.HPChar

class MainActivity : AppCompatActivity() {

    private lateinit var hpCharDAO: HPCharDAO
    private lateinit var listView: ListView
    private lateinit var emptyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lvChars)
        emptyTextView = findViewById(R.id.tvEmpty)
        hpCharDAO = HPCharDAO(this)

        listAllChars()

        // Listener para clicar nos itens da lista e abrir a tela de detalhes
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedChar = parent.getItemAtPosition(position) as HPChar
            val intent = Intent(this, NewHPChar::class.java).apply {
                putExtra("charID", selectedChar.id)
            }
            startActivity(intent)
        }
    }

    //Carrega lista de personagens no ListView
    private fun listAllChars() {
        val hpChars = hpCharDAO.getAllChars()
        if (hpChars.isEmpty()) {
            listView.visibility = ListView.GONE
            emptyTextView.visibility = TextView.VISIBLE
        } else {
            listView.visibility = ListView.VISIBLE
            emptyTextView.visibility = TextView.GONE
            val adapter: ArrayAdapter<HPChar> = ArrayAdapter(this, android.R.layout.simple_list_item_1, hpChars)
            listView.adapter = adapter
        }
    }

    fun newChar(view: View) {
        val intent = Intent(this, NewHPChar::class.java)
        startActivity(intent)
    }

    // Atualiza a lista quando voltar de outra activity (adição, edição, etc.)
    override fun onResume() {
        super.onResume()
        listAllChars()
    }
}
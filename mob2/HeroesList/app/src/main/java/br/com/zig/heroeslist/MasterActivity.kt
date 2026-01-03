package br.com.zig.heroeslist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {
    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.rvHeroes)
        recyclerView?.adapter = HeroesAdapter(createHeroes(), this) {
            Toast.makeText(this, "Clicou no ${it.heroName}", Toast.LENGTH_SHORT).show()
        }
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun createHeroes(): List<Hero> {
        return listOf(
            Hero(R.drawable.fatman, "Fatman", "FOX BC"),
            Hero(R.drawable.bartman, "Bartman", "FOX BC"),
            Hero(R.drawable.duffman, "Duffman", "FOX BC"),
            Hero(R.drawable.pie_man, "Pieman", "FOX BC"),
            Hero(R.drawable.boogerman, "Boogerman", "Interplay Entertainment"),
            Hero(R.drawable.homem_libelula, "Dragonfly Man", "Craig Mazin Company"),
            Hero(R.drawable.homem_radioativo, "Radioactive Man", "Android’s Dungeon"),
            Hero(R.drawable.homem_sereia, "Mermaid Man", "Nickelodeon"),
            Hero(R.drawable.mexilhaozinho, "Barnacle Boy", "Nickelodeon"),
            Hero(R.drawable.queixo_rubro, "Crimson Chin", "Nickelodeon"),
        )
    }
}
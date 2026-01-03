package br.com.zig.heroeslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeroesAdapter(
    private val heroes: List<Hero>,
    private val context: Context,
    private val click: (hero: Hero) -> Unit
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {
    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heroImage = itemView.findViewById<ImageView>(R.id.heroImage)
        val heroName = itemView.findViewById<TextView>(R.id.tvHeroeName)
        val heroCompany = itemView.findViewById<TextView>(R.id.tvHeroCia)

        fun bind(hero: Hero) {
            heroImage.setImageResource(hero.heroImage)
            heroName.text = hero.heroName
            heroCompany.text = hero.heroCompany
            itemView.setOnClickListener { click(hero) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return HeroesViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}

package br.com.zig.harrypotterdbapp.model

class HPChar(
    val id: Int = 0,
    val name: String,
    val house: String,
    val ancestry: String
) {
    override fun toString(): String {
        return name // Retorna apenas o nome para exibir na lista de personagens
    }
}
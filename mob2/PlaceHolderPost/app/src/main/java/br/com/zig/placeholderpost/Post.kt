package br.com.zig.placeholderpost

class Post (
    val userId: Int,
    val id: Int? = null, // Gerado automaticamente no POST
    val title: String,
    val body: String
)
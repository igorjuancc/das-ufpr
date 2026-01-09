package br.com.zig.placeholderpost

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostApi {
    // Requisição GET para obter um post específico
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Post

    // Requisição POST para criar um novo post
    @POST("posts")
    suspend fun createPost(@Body newPost: Post): Post

    // Requisição PUT para atualizar um post existente
    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int,
                           @Body updatePost: Post): Post

    // Requisição DELETE para apagar um post específico
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int)
}
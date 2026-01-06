package br.com.zig.catfactapp

import retrofit2.http.GET

interface CatFactApi {
    @GET("fact")
    suspend fun getCatFact(): CatFact
}
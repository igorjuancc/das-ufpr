package br.com.zig.dogimageapp

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breed/{breed}/images/random")
    suspend fun getRandomDogImage(@Path("breed") breed: String): DogResponse
}
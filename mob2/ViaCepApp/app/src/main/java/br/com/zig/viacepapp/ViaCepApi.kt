package br.com.zig.viacepapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepApi {
    @GET("ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): Address
}
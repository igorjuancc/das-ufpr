package br.com.zig.viacepapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address (
    val cep: String,
    @SerializedName("logradouro")
    val street: String,
    @SerializedName("complemento")
    val number: String,
    @SerializedName("localidade")
    val city: String,
    @SerializedName("estado")
    val state: String
) : Serializable
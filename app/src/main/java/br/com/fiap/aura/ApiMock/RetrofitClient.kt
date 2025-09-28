package br.com.fiap.aura.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/") // URL base da API ReqRes
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val checkInApi: CheckInApi by lazy {
        retrofit.create(CheckInApi::class.java)
    }
}
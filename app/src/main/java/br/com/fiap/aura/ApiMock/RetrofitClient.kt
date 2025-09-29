package br.com.fiap.aura.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in/api/" // URL base da API ReqRes

    // Retrofit único para todas as APIs
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instâncias das APIs
    val checkInApi: CheckInApi by lazy {
        retrofit.create(CheckInApi::class.java)
    }

    val cargaApi: CargaApi by lazy {
        retrofit.create(CargaApi::class.java)
    }
}

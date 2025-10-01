package br.com.fiap.aura.api

import br.com.fiap.aura.ApiMock.AlertasApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https:localhost:" // Falta colocar a porta que o pablo falou

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
    val alertasApi: AlertasApi by lazy {
        retrofit.create(AlertasApi::class.java)
}}

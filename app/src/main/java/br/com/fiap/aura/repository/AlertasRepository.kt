package br.com.fiap.aura.repository

import br.com.fiap.aura.api.AlertasApi
import br.com.fiap.aura.model.AlertasModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlertasRepository {

    private val api: AlertasApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // localhost no emulador
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AlertasApi::class.java)
    }

    suspend fun enviarAlertas(respostas: List<AlertasModel>) {
        api.enviarAlertas(respostas)
    }

    suspend fun listarAlertas(): List<List<AlertasModel>> {
        val response = api.listarAlertas()
        return if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }
}

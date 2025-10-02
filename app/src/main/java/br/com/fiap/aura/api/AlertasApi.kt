package br.com.fiap.aura.api

import br.com.fiap.aura.model.AlertasModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlertasApi {

    @POST("{id}")
    suspend fun enviarAlertas(@Body respostas: List<AlertasModel>): Response<Unit>

    @GET("{id}")
    suspend fun listarAlertas(): Response<List<List<AlertasModel>>>
}

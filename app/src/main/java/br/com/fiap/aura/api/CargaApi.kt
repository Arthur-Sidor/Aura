package br.com.fiap.aura.api

import br.com.fiap.aura.model.CargaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CargaApi {

    // Busca todas as respostas registradas (histórico)
    @GET("respostas-carga")
    suspend fun getHistorico(): Response<List<CargaModel>>

    // Envia uma nova resposta do formulário
    @POST("responder")
    suspend fun enviarResposta(@Body resposta: CargaModel): Response<CargaModel>
}

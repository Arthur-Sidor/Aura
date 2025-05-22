package br.com.fiap.aura.ApiMock

import br.com.fiap.aura.model.AvaliacaoRiscosRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AvaliacaoRiscosApi {
    @POST("avaliacao")
    suspend fun enviarRespostas(@Body request: AvaliacaoRiscosRequest): Response<Unit>
}
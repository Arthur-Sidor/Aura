package br.com.fiap.aura.ApiMock

import br.com.fiap.aura.model.AvaliacaoRiscosRequest
import br.com.fiap.aura.model.BemEstarRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SupabaseApi {

    // Inserir um novo registro na tabela "bemestar"
    @POST("bemestar")
    suspend fun enviarCheckin(@Body request: BemEstarRequestModel): Response<Void>

    // Exemplo para avaliação de riscos
    @POST("avaliacao")
    suspend fun enviarRespostas(
        @Body request: AvaliacaoRiscosRequest


    )}
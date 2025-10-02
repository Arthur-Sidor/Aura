package br.com.fiap.aura.api


import br.com.fiap.aura.model.RelacionamentosModel

import br.com.fiap.aura.model.ResponseRelacionamentosModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RelacionamentoApi {

    /**
     * Busca todos os relacionamentos disponíveis para o usuário
     */
    @GET("relacionamentos") // Ajuste conforme o endpoint real
    suspend fun getRelacionamentos(): List<RelacionamentosModel>

    /**
     * Salva a resposta do usuário para um relacionamento
     */
    @POST("respostas") // Ajuste conforme o endpoint real
    suspend fun salvarResposta(
        @Body response: ResponseRelacionamentosModel
    ): ResponseRelacionamentosModel
}

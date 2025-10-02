package br.com.fiap.aura.api

import br.com.fiap.aura.model.LiderancaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ComunicacaoApi {

    @POST("api/ResponseRelatorio")
    suspend fun enviarResposta(
        @Body avaliacao: LiderancaModel
    ): Response<Void>  // pode ser outro tipo, depende do retorno do backend

    @GET("api/ResponseRelatorio")
    suspend fun getHistorico(): Response<List<LiderancaModel>>
}

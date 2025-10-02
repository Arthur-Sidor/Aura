package br.com.fiap.aura.api


import br.com.fiap.aura.model.LiderancaModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LiderancaApi {

    // Endpoint para enviar a avaliação de liderança
    @POST("lideranca/responder")
    suspend fun enviarResposta(@Body avaliacao: LiderancaModel): Response<Void>

    // Endpoint para buscar histórico de avaliações
    @GET("lideranca/historico")
    suspend fun getHistorico(): Response<List<LiderancaModel>>
}

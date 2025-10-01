package br.com.fiap.aura.Viewmodel


import br.com.fiap.aura.model.RelacionamentosModel
import br.com.fiap.aura.model.ResponseRelacionamentosModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Retrofit API interface
interface RelacionamentoApi {

    @GET("relacionamentos") // endpoint para pegar os relacionamentos
    suspend fun getRelacionamentos(): List<RelacionamentosModel>

    @POST("respostas") // endpoint para salvar respostas
    suspend fun salvarResposta(@Body response: ResponseRelacionamentosModel): ResponseRelacionamentosModel
}

// Repository
class RelacionamentoRepository {

    private val api: RelacionamentoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://seu-backend.com/api/") // substitua pela URL do seu backend
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RelacionamentoApi::class.java)
    }

    suspend fun getRelacionamentos(): List<RelacionamentosModel> {
        return api.getRelacionamentos()
    }

    suspend fun salvarResposta(response: ResponseRelacionamentosModel): ResponseRelacionamentosModel {
        val result = api.salvarResposta(response)
        if (result != null) {
            return result
        } else {
            throw Exception("Falha ao salvar resposta")
        }
    }
}

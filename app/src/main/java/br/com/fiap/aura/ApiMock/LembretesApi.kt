package br.com.fiap.aura.ApiMock

import br.com.fiap.aura.model.LembreteModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LembretesApi {
    @GET("lembretes")  // supondo que a rota para lembretes seja /lembretes no mockapi
    fun getLembretes(): Call<List<LembreteModel>>
}
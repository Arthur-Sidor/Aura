package br.com.fiap.aura.ApiMock

import br.com.fiap.aura.model.LembreteModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LembretesApi {
    @GET("lembretes")
    fun getLembretes(): Call<List<LembreteModel>>
}
package br.com.fiap.aura.ApiMock

import br.com.fiap.aura.model.BemEstarRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BemEstarApi {
    @POST("bemestar")
    suspend fun enviarCheckin(@Body request: BemEstarRequestModel): Response<Void>
}

package br.com.fiap.aura.api

import br.com.fiap.aura.model.CheckInModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CheckInApi {
    @POST("{id}") // Substitua pela rota real da API do Pablo
    suspend fun enviarCheckin(@Body checkIn: CheckInModel): Response<Unit>

    @GET("{id}")
    suspend fun listarCheckins(): Response<List<CheckInModel>>

}

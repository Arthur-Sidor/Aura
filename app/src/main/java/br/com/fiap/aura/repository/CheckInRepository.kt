package br.com.fiap.aura.repository

import br.com.fiap.aura.api.CheckInApi
import br.com.fiap.aura.api.RetrofitClient
import br.com.fiap.aura.model.CheckInModel

import retrofit2.Response

class CheckInRepository(private val api: CheckInApi = RetrofitClient.checkInApi) {

    suspend fun enviarCheckin(checkIn: CheckInModel): Response<Unit> {
        return api.enviarCheckin(checkIn)
    }

    suspend fun listarCheckins(): Response<List<CheckInModel>> {
        return api.listarCheckins()
    }
}

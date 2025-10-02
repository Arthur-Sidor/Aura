package br.com.fiap.aura.api

import br.com.fiap.aura.model.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>

    @POST("/api/user/register")
    suspend fun register(@Body request: RegisterRequestModel): Response<RegisterResponseModel>
}

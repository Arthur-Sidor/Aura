package br.com.fiap.aura.repository

import br.com.fiap.aura.api.AuthApi
import br.com.fiap.aura.model.auth.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepository {

    private val api: AuthApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/") // Emulador -> back C#
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AuthApi::class.java)
    }

    // Login
    suspend fun login(username: String, password: String): Response<LoginResponseModel> {
        return api.login(LoginRequestModel(username, password))
    }

    // Register
    suspend fun register(request: RegisterRequestModel): Response<RegisterResponseModel> {
        return api.register(request)
    }
}

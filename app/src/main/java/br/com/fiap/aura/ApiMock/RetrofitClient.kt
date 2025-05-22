package br.com.fiap.aura.ApiMock

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://682e99fb746f8ca4a47d9e95.mockapi.io/api/v1/") // URL mockapi
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AvaliacaoRiscosApi = retrofit.create(AvaliacaoRiscosApi::class.java)

    val bemEstarApi: BemEstarApi by lazy {
        retrofit.create(BemEstarApi::class.java)
    }
    val lembretesApi: LembretesApi by lazy {
        retrofit.create(LembretesApi::class.java)
    }
}


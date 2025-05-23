package br.com.fiap.aura.ApiMock

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

object SupabaseClient {

    private const val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh3YmlmbmdoaW1vYWltdHFjYmprIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0Nzk1OTEwNCwiZXhwIjoyMDYzNTM1MTA0fQ.HcyHeNQYsIGtebkRA9WfwYHqxTbw59Nfrn6XJcwmcIs"
    private const val BASE_URL = "https://xwbifnghimoaimtqcbjk.supabase.co"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer $API_KEY")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}









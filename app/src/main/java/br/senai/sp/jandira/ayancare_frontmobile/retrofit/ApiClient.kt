package br.senai.sp.jandira.ayancare_frontmobile.retrofit

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PatientService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ApiClient(authToken: String) {

    private val baseUrl = "https://ayancare-api.cyclic.cloud"
    private val authToken = authToken

    private val interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val builder: Request.Builder = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .header("Content-Type", "application/json") // Você pode deixar essa linha ou remover dependendo da necessidade
        val newRequest: Request = builder.build()
        chain.proceed(newRequest)
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val apiService: PatientService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PatientService::class.java)
}
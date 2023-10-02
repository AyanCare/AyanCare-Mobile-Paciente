package br.senai.sp.jandira.ayancare_frontmobile.retrofit

import br.senai.sp.jandira.ayancare_frontmobile.PacienteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://ayancare-api.cyclic.cloud"
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPatient(): PacienteService {
        return retrofitFactory.create(PacienteService::class.java)
    }

}
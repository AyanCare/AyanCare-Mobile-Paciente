package br.senai.sp.jandira.ayancare_frontmobile.retrofit

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.ConectarService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero.service.GeneroService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PacienteService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.ResponsavelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    //private const val BASE_URL = "http://10.107.144.18:8080"
    //private const val BASE_URL = "https://ayancare-api.cyclic.cloud"
    private const val BASE_URL = "http://192.168.0.120:8080" //192.168.0.116
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

    fun getResponsible(): ResponsavelService{
        return retrofitFactory.create(ResponsavelService::class.java)
    }

    fun getGenero(): GeneroService{
        return retrofitFactory.create(GeneroService::class.java)
    }

    fun getConectar(): ConectarService{
        return retrofitFactory.create(ConectarService::class.java)
    }

}
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.MedidasResponse
import retrofit2.http.GET

interface MedidasService {

    @GET("/v1/ayan/medidas")
    fun getMedidas(): retrofit2.Call<MedidasResponse>
}
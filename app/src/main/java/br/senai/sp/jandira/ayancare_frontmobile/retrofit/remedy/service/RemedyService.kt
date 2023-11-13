package br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy.RemedyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemedyService {

    @GET("/v2/ayan/medicamentos")
    fun getRemedy(@Query("idPaciente") idPaciente: Int): retrofit2.Call<RemedyResponse>

}
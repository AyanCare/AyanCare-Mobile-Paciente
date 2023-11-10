package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlarmeService {

    @GET("/v1/ayan/alarmes/unitario")
    fun getAlarmesByIdPaciente(@Query("idPaciente") idPaciente: Int): Call<AlarmeResponse>

}
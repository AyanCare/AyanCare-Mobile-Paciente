package br.senai.sp.jandira.ayancare_frontmobile

import retrofit2.http.GET
import retrofit2.http.Path

interface PacienteService {
    @GET("/v1/ayan/paciente/{id}")
    fun getPatient(@Path("id") id: String): retrofit2.Call<PacienteList>

}
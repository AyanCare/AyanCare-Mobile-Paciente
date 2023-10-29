package br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.CuidadorResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CuidadorService {

    @GET("/v1/ayan/cuidador/{id}")
    fun getCuidadorByID(@Header("x-access-token") token: String, @Path("id") id: String?): retrofit2.Call<CuidadorResponse>

}
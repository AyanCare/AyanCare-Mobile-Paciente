package br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.TesteHumorResponse
import retrofit2.http.GET

interface TesteHumorService {

    @GET("/v1/ayan/opcoes")
    fun getTesteHumor(): retrofit2.Call<TesteHumorResponse>

}
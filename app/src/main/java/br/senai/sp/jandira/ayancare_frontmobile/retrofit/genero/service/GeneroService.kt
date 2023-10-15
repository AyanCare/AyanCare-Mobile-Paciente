package br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.genero.GeneroResponse
import retrofit2.http.GET

interface GeneroService {

    @GET("/v1/ayan/generos")
    fun getGenero(): retrofit2.Call<GeneroResponse>

}
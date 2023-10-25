package br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.TesteHumorResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TesteHumorService {

    @GET("/v1/ayan/opcoes")
    fun getTesteHumor(): retrofit2.Call<TesteHumorResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/teste")
    suspend fun createTesteHumor(@Body body: JsonObject): Response<JsonObject>

}
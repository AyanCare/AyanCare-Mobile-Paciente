package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.DoencaResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ContatoResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PacienteService {

    @GET("/v1/ayan/paciente/{id}")
    fun getPatientById(@Path("id") id: String): retrofit2.Call<PacienteResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/doenca")
    suspend fun createChronicDiseases(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/v1/ayan/doenca/{id}")
    fun deleteChronicDiseases(@Path("id") id: Int): Call<DoencaResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/comorbidade")
    suspend fun createComorbidity(@Body body: JsonObject): Response<JsonObject>

    @DELETE("/v1/ayan/comorbidade/{id}")
    fun deleteComorbidity(@Path("id") id: Int): Call<DoencaResponse>

}
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PatientResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientService {

    //https://ayancare-api.cyclic.cloud/paciente/2

    @Headers("Content-Type: application/json")
    @GET("/v1/ayan/paciente/{id}")
    fun getPatientById(
        @Header("x-access-token") token: String,
        @Path("id") id: Int
    ): Call<PatientResponse>


    @Headers("Content-Type: application/json")
    @POST("/paciente")
    suspend fun createPatient(@Body body: JsonObject): Response<JsonObject>


}
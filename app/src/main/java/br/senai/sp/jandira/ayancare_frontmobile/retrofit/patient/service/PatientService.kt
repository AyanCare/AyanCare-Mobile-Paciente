package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientService {

    //http://localhost:8080/v1/ayan/paciente

    @Headers("Content-Type: application/json")
    @GET("/paciente")
    fun getPatientById(@Path("id") id: Long): Call<Patient>


    @Headers("Content-Type: application/json")
    @POST("/paciente")
    suspend fun createPatient(@Body body: JsonObject): Response<JsonObject>


}
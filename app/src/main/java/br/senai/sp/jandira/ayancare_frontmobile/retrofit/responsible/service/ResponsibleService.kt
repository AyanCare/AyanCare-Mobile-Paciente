package br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ResponsibleResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ResponsibleService {

    //http://localhost:8080/v1/ayan/contato

    //pegar os contatos daquele paciente
    @Headers("Content-Type: application/json")
    @GET("/contato")
    suspend fun getResponsibleByPatientId(@Path("id") id: Long): Call<ContactList>

    //inserir um contato
    @Headers("Content-Type: application/json")
    @POST("/contato")
    suspend fun createResponsible(@Body body: JsonObject): Response<JsonObject>

}
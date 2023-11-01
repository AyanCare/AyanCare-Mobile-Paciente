package br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ResponsavelResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ResponsavelService {

    //http://localhost:8080/v1/ayan/contato

    @GET("/v1/ayan/contatos")
    fun getTodosResponsaveis(): Call<ResponsavelResponse>

    @GET("/v1/ayan/contato/responsavel/{id}")
    fun getTodosResponsaveisByIdPaciente(@Path("id") id: Int): Call<ResponsavelResponse>

    @GET("/v1/ayan/contatos/")
    fun getResponsavelByPacienteId(@Query("idContatoPaciente") idContatoPaciente: Long): Call<ResponsavelResponse>


    //inserir um contato
    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/contato")
    suspend fun createResponsible(@Body body: JsonObject): Response<JsonObject>


    @DELETE("/v1/ayan/contato/{id}")
    fun deleteResponsible(@Path("id") id: Int): Call<ResponsavelResponse>

}
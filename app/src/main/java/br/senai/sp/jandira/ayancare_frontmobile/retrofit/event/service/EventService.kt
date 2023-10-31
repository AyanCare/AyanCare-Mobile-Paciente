package br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.EventResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EventService {

    @GET("/v1/ayan/eventos")
    fun getEventsByIdPaciente(@Query("idPaciente") idPaciente: Int): Call<EventResponse>

    @POST("/v1/ayan/evento")
    fun createEvent(@Body body: JsonObject): Response<JsonObject>

}
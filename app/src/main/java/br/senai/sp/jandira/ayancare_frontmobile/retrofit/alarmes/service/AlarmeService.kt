package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmeUnitariosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmesResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AlarmeService {

    @GET("/v1/ayan/alarmes/unitario")
    fun getAlarmesUnitariosByIdPaciente(@Query("idPaciente") idPaciente: Int): Call<AlarmeUnitariosResponse>

    @GET("/v1/ayan/alarmes")
    fun getAlarmesByIdPaciente(@Query("idPaciente") idPaciente: Int): Call<AlarmesResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/medicamento")
    suspend fun createMedicamento(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/alarme")
    suspend fun createAlarme(@Body body: JsonObject): Response<JsonObject>

}
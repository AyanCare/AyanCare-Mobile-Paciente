package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmeUnitariosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmesResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.MedicamentoResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.MedicamentosResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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




    @GET("/v1/ayan/medicamentos")
    fun getMedicamentosByIdPaciente(@Query("idPaciente") idPaciente: Int): Call<MedicamentosResponse>

    @GET("/v1/ayan/medicamento/{id}")
    fun getMedicamentosById(@Path("id") id: Int): Call<MedicamentoResponse>

    @Headers("Content-Type: application/json")
    @PUT("/v2/ayan/medicamento")
    suspend fun updateMedicamento(@Body body: JsonObject): Response<JsonObject>

}
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    //https://ayancare-api.cyclic.cloud

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/paciente")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("/v1/ayan/paciente")
    suspend fun updateUser(@Header("x-access-token") token: String,  @Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/usuario/autenticar")
    suspend fun postUser(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/usuario/esqueciasenha")
    suspend fun requestPasswordReset(@Body requestBody: JsonObject): Response<JsonObject>
}
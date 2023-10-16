package br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service


import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ConectarService {

    @GET("/v1/ayan/paciente/conectados/{id}")
    fun getConectar(@Path("id") id: Int): retrofit2.Call<ConectarResponse>

    @POST("/v1/ayan/paciente/conectar")
    fun createConect(@Query("idPaciente") idPaciente: Int, @Query("idCuidador") idCuidador: Int): retrofit2.Call<ConectarResponse>

}
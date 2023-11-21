package br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao.NotificacaoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificacaoService {

    @GET("/v1/ayan/notificacoes")
    fun getNotificacaobyIdPaciente(@Query("idPaciente") idPaciente: Int): retrofit2.Call<NotificacaoResponse>

}
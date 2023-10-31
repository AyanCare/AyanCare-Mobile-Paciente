package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.EventService
import com.google.gson.JsonObject
import retrofit2.Response

class EventRepository {

    private val apiService = RetrofitFactory.getInstance().create(EventService::class.java)

    suspend fun registerEvent(
        nome: String,
        descricao: String,
        local: String,
        hora: String,
        dia: String,
        idPaciente: Int,
        idCuidador: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("descricao", descricao)
            addProperty("local", local)
            addProperty("hora", hora)
            addProperty("dia", dia)
            addProperty("idPaciente", idPaciente)
            addProperty("idCuidador", idCuidador)

        }
        return apiService.createEvent(requestBody)
    }
}

//    {
//        "nome":"Jhon Doe único 2",
//        "descricao":"testes teste testes",
//        "local":"no Postman",
//        "hora":"12:00",
//        "dia":"2005-01-21",
//        "idPaciente":73,
//        "idCuidador":6
//    }

package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeService
import com.google.gson.JsonObject
import retrofit2.Response

class AlarmeRepository {

    private val apiService = RetrofitFactory.getInstance().create(AlarmeService::class.java)

    suspend fun registerAlarme(
        dia: String,
        intervalo: Int,
        horario: String,
        id_medicamento: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("dia", dia)
            addProperty("intervalo", intervalo)
            addProperty("horario", horario)
            addProperty("id_medicamento", id_medicamento)
        }
        return apiService.createAlarme(requestBody)
    }

    suspend fun registerAlarmeUnitario(
        quantidade: Int,
        id_alarme_medicamento: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("quantidade", quantidade)
            addProperty("id_alarme_medicamento", id_alarme_medicamento)
        }
        return apiService.createAlarmeUnitario(requestBody)
    }

    suspend fun updateAlarmeUnitario(
        id_status_alarme: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id_status_alarme", id_status_alarme)
        }
        return apiService.createAlarme(requestBody)
    }

}

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


}

//{
//    "dia":"2023-09-12",
//    "intervalo": 20000,
//    "horario": "17:30:00",
//    "id_medicamento": 10
//}
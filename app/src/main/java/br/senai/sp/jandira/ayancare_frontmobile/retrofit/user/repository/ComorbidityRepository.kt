package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PacienteService
import com.google.gson.JsonObject
import retrofit2.Response

class ComorbidityRepository {
    private val apiService = RetrofitFactory.getInstance().create(PacienteService::class.java)

    suspend fun registerComorbidity(
        nome: String,
        id_paciente: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("id_paciente", id_paciente)
        }

        return apiService.createComorbidity(requestBody)

    }
}
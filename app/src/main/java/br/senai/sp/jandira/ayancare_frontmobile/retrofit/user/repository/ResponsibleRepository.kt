package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service.UserService
import com.google.gson.JsonObject
import retrofit2.Response

class ResponsibleRepository {
    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun registerResponsible(
        nome: String,
        numero: String,
        local: String,
        id_paciente: Int,
        id_status_contato: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("numero", numero)
            addProperty("local", local)
            addProperty("id_paciente", id_paciente)
            addProperty("id_status_contato", id_status_contato)
        }

        return apiService.createUser(requestBody)
    }
}
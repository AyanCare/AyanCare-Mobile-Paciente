package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.testeHumor.service.TesteHumorService
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response

class TesteHumorRepository {

    private val apiService = RetrofitFactory.getInstance().create(TesteHumorService::class.java)

    suspend fun registerTesteHumor(
        observacao: String,
        id_paciente: Int,
        escolhas: List<String>,
        data: String
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("observacao", observacao)
            addProperty("id_paciente", id_paciente)

            // Criar um JsonArray para as escolhas
            val escolhasArray = JsonArray()
            escolhas.forEach { escolha ->
                escolhasArray.add(escolha)
            }
            add("escolhas", escolhasArray)
            addProperty("data", data)

        }

        return apiService.createTesteHumor(requestBody)
    }
}
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service.UserService
import com.google.gson.JsonObject
import retrofit2.Response

class SuggestionRepository {

    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun registerSuggestion(
        nome: String,
        email: String,
        sugestao: String
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("email", email)
            addProperty("sugestao", sugestao)
        }

        return apiService.postSuggestion(requestBody)

    }

}
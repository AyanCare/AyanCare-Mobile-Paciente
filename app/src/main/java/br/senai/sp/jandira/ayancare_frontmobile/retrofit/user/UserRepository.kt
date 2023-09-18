package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service.UserService
import com.google.gson.JsonObject
import retrofit2.Response

class UserRepository {
    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun registerUser(nomeDeUsuario: String, email: String, senha: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome_de_usuario", nomeDeUsuario)
            addProperty("email", email)
            addProperty("senha", senha)
        }

        return apiService.createUser(requestBody)
    }
}
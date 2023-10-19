package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service.UserService
import com.google.gson.JsonObject
import retrofit2.Response

class CadastroRepository {
    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun registerUser(nome: String, email: String, senha: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("email", email)
            addProperty("senha", senha)
        }

        return apiService.createUser(requestBody)
    }


    suspend fun updateUser(
        token: String,
        id: Int,
        nome: String,
        data_nascimento: String,
        email: String,
        senha: String,
        cpf: String,
        id_endereco_paciente: Int,
        genero: String
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
            addProperty("nome", nome)
            addProperty("data_nascimento", data_nascimento)
            addProperty("email", email)
            addProperty("senha", senha)
            addProperty("cpf", cpf)
            addProperty("id_endereco_paciente", id_endereco_paciente)
            addProperty("genero", genero)
        }

        return apiService.updateUser(token, requestBody)
    }

}
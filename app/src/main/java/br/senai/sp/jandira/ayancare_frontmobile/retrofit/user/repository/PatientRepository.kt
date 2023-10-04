package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PacienteService
import com.google.gson.JsonObject
import retrofit2.Response

class PatientRepository {

    private val apiService = RetrofitFactory.getInstance().create(PacienteService::class.java)

    suspend fun registerPatient(
        nome: String,
        data_nascimento: String,
        email: String,
        senha: String,
        cpf: String,
        id_endereco_paciente: Int,
        id_genero: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {

            addProperty("nome", nome)
            addProperty("data_nascimento", data_nascimento)
            addProperty("email", email)
            addProperty("senha", senha)
            addProperty("cpf", cpf)
            addProperty("id_endereco_paciente", id_endereco_paciente)
            addProperty("id_genero", id_genero)

        }

        return apiService.createPatient(requestBody)

    }

}

//    "nome": "Lohannes",
//    "data_nascimento": "2005-01-21",
//    "email": "lohannes.s26@gmail.com",
//    "senha": "EkkoDeZa1@",
//    "cpf": "98765498721",
//    "id_endereco_paciente": 1,
//    "id_genero": 3
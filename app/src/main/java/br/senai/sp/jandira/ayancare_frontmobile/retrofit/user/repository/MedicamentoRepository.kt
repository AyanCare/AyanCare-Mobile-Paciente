package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeService
import com.google.gson.JsonObject
import retrofit2.Response

class MedicamentoRepository {

    private val apiService = RetrofitFactory.getInstance().create(AlarmeService::class.java)

    suspend fun registerMedicamento(
        nome: String,
        quantidade: Int,
        data_validade: String,
        estocado: Int,
        id_paciente: Int,
        id_medida: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("quantidade", quantidade)
            addProperty("data_validade", data_validade)
            addProperty("estocado", estocado)
            addProperty("id_paciente", id_paciente)
            addProperty("id_medida", id_medida)
        }
        return apiService.createMedicamento(requestBody)
    }
}


package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

data class Medicamentos(
    val id_medicamento: Int,
    val nome: String,
    val quantidade: Int,
    val data_validade: String,
    val estocado: Int,
    val limite: Int,
    val id_paciente: Int,
    val paciente: String
)
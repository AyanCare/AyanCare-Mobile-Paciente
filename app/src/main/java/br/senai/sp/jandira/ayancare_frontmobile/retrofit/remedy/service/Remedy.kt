package br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy.service

data class Remedy(
    val id: Int,
    val nome: String,
    val quantidade: Int,
    val data_validade: String,
    val id_medida: Int,
    val id_paciente: Int,
    val estocado: Int
)

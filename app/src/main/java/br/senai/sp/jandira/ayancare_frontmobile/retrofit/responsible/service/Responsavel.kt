package br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service

data class Responsavel(
    val id: Int,
    val nome: String,
    val numero: String,
    val local: String,
    val id_paciente: Int,
    val id_status_contato: Int
)

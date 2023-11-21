package br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao.service

data class Notificacao(
    val id: Int,
    val nome: String,
    val descricao: String,
    val data_criacao: String,
    val hora_criacao: String,
    val id_paciente: String,
    val paciente: String
)
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.service

data class Cuidador(
    val id: Int,
    val nome: String,
    val foto: String,
    val data_nascimento: String,
    val descricao_experiencia: String,
    val genero: String,
    val endereco_id: Int,
    val logradouro: String,
    val bairro: String,
    val numero: Int,
    val cep: String,
    val cidade: String,
    val estado: String,
)

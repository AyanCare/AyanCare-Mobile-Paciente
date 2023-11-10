package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

data class Alarme(
    val id_alarme_unitario: Int,
    val id_medicamento: Int,
    val medicamento: String,
    val foto: String,
    val id_alarme: Int,
    val data_criacao: String,
    val intervalo: Int,
    val horario_inicial: String,
    val quantidade_retirada: Int,
    val id_medida: Int,
    val medida: String,
    val medida_sigla: String,
    val status: String,
    val id_paciente: Int,
    val paciente: String
)
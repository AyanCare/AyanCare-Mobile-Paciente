package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service

data class Alarme(
    val paciente: String,
    val id: Int,
    val dia: String,
    val intervalo: Int,
    val horario: String,
    val id_medicamento: Int,
    val medicamento: String
)
package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service

data class EventosSemanaisSemanal(
    val id: Int,
    val id_paciente: Int,
    val paciente: String,
    val id_cuidador: Int,
    val cuidador: String,
    val nome: String,
    val descricao: String,
    val local: String,
    val dias: List<Dias>
)

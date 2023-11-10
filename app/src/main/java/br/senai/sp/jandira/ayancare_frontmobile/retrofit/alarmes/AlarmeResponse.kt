package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Alarme

data class AlarmeResponse(
    val status: Int,
    val quantidade: String,
    val alarme: List<Alarme>
)
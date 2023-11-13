package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Alarme

data class AlarmesResponse(
    val status: Int,
    val alarme: List<Alarme>
)

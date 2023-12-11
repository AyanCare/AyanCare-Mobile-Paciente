package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.CalendarioSemanal

data class CalendarioSemanalResponse(
    val status: Int,
    val calendario: CalendarioSemanal
)

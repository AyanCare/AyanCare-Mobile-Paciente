package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeUnitario

data class AlarmeUnitariosResponse(
    val status: Int,
    val quantidade: String,
    val alarme: List<AlarmeUnitario>
)
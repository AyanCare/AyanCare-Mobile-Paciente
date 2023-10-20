package br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.medidas.service.Medidas


data class MedidasResponse(
    val status: Int,
    val quantidade: Int,
    val medidas: List<Medidas>
)

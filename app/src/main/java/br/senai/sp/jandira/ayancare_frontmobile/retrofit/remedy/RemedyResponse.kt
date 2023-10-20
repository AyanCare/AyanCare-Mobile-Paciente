package br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.remedy.service.Remedy

data class RemedyResponse(
    val status: Int,
    val medicamento: List<Remedy>
)
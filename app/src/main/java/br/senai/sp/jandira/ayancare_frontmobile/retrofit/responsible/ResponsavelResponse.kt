package br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.Responsavel

data class ResponsavelResponse(
    val status: Int,
    val contatos: List<Responsavel>
)

package br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamento

data class MedicamentoResponse(
    val status: Int,
    val medicamento: Medicamento
)

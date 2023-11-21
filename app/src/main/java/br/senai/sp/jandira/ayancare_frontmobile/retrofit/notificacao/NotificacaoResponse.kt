package br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.notificacao.service.Notificacao

data class NotificacaoResponse(
    val status: Int,
    val notificacao: List<Notificacao>
)

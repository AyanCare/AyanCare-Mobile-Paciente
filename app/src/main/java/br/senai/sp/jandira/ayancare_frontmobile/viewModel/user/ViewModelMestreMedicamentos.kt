package br.senai.sp.jandira.ayancare_frontmobile.viewModel.user

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.Alarmes

class ViewModelMestreMedicamentos: ViewModel() {
    var lista: List<Alarmes> = listOf()
}
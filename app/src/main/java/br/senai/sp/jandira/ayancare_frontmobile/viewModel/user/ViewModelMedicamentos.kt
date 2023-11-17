package br.senai.sp.jandira.ayancare_frontmobile.viewModel.user

import androidx.lifecycle.ViewModel

class ViewModelMedicamentos: ViewModel() {
    var id: Int = 0
    var id_paciente: Int = 0
    var paciente: String = ""
    var id_medicamente: Int = 0
    var medicamento: String = ""
    var horario: String = ""
    var intervalo: String = ""
    var id_status: Int = 0
    var status: String = ""
}
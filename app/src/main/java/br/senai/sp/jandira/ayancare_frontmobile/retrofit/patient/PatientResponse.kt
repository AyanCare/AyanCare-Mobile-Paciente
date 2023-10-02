package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Patient

data class PatientResponse (
    var status: Int,
    var paciente: Patient
)
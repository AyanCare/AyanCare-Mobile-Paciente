package br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible

import com.google.gson.annotations.SerializedName

data class ResponsibleResponse(
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("nome") var nome: String = "",
    @SerializedName("numero") var numero: String = "",
    @SerializedName("local") var local: String = "",
    @SerializedName("id_paciente") var id_paciente:Int? = 0,
    @SerializedName("id_status_contato") var id_status_contato:Int? = 0,

)

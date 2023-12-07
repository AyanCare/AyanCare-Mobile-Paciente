package br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.cadastro.components.ProgressBar
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddChronicDiseases
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddComorbidity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AddDiseaseScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    val context = LocalContext.current
    var isDialogVisibleChronicDiseases by remember { mutableStateOf(false) }
    var isDialogVisibleComorbidity by remember { mutableStateOf(false) }

    // Mantenha uma lista de  patients no estado da tela
    var listPaciente by remember {
        mutableStateOf(
            Paciente(
                0, "", "", "", "", "", "", "",
                emptyList(),
                emptyList()
            )
        )
    }

    val id = localStorage.lerValor(context, "id_paciente")

    var call = RetrofitFactory.getPatient().getPatientById(id = id.toString())

    call.enqueue(object : Callback<PacienteResponse> {
        override fun onResponse(
            call: Call<PacienteResponse>,
            response: Response<PacienteResponse>
        ) {
            listPaciente = response.body()!!.paciente
            Log.e("TAG", "onResponse: $listPaciente", )
        }
        override fun onFailure(call: Call<PacienteResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Adicione",
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Adiocione as doenças crõnicas e a as comorbidades do paciente",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Doenças Crôninas",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleChronicDiseases = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "",
                            tint = Color(0xFF35225F),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
                if (isDialogVisibleChronicDiseases) {
                    ModalAddChronicDiseases(
                        isDialogVisibleChronicDiseases = false,
                        navController = navController,
                        lifecycleScope = lifecycleScope,
                        nav = "add_disease_screen"
                    )
                }
                LazyRow() {
                    items(listPaciente.doencas_cronicas.reversed()) {

                        var text = if (listPaciente.doencas_cronicas[0].nome == null){
                            "Não Existe Doenças Crônicas"
                        } else {
                            "${it.nome}"
                        }
                        ProcessingProfile(
                            text = text,
                            width = 160,
                            onValueChange = {

                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cormobidade",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleComorbidity = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "",
                            tint = Color(0xFF35225F),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
                if (isDialogVisibleComorbidity) {
                    ModalAddComorbidity(
                        isDialogVisibleComorbidity = false,
                        navController = navController,
                        lifecycleScope= lifecycleScope,
                        nav = "add_disease_screen"
                    )
                }

                LazyRow() {
                    items(listPaciente.comorbidades.reversed()) {

                        var text = if (listPaciente.comorbidades[0].nome == null){
                            "Não Existe Comorbidades"
                        } else {
                            "${it.nome}"
                        }
                        ProcessingProfile(
                            text = text,
                            width = 150,
                            onValueChange = {

                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

            }

            Column(
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(
                    text = "Finalizar",
                    onClick = {
                        navController.navigate("tela_instrucao1_screen")
                    }
                )
            }
            ProgressBar(text = "3 / 3", valor = 330)

        }
    }
}
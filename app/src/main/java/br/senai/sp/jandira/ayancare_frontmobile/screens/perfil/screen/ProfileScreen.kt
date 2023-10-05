package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CardMedicine
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ProfileScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

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

    var call = RetrofitFactory.getPatient().getPatientById(id = id.toString())


    call.enqueue(object : Callback<PacienteResponse> {
        override fun onResponse(
            call: Call<PacienteResponse>,
            response: Response<PacienteResponse>
        ) {
            listPaciente = response.body()!!.paciente
        }
        override fun onFailure(call: Call<PacienteResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(end = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = {
                        navRotasController.navigate("edit_profile_screen")
                    },
                    modifier = Modifier
                        .width(105.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(Color(249, 241, 237))
                ) {
                    Text(
                        text = "Editar perfil",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                    .fillMaxSize()
            ) {
                CircleProfile(
                    painter = painterResource(id = R.drawable.instrucao3)
                )

                Text(
                    text = listPaciente.nome,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000)
                )

                Text(
                    text = "Paciente",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Doenças Crônicas",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

                Spacer(modifier = Modifier.height(5.dp))


                LazyRow() {
                    items(listPaciente.doencas_cronicas.reversed()) {

                        var text = if (listPaciente.doencas_cronicas[0].nome == null){
                            "Não Existe Comorbidades"
                        } else {
                            "${it.nome}"
                        }

                        ProcessingProfile(
                            text = text
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Comorbidade",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

                Spacer(modifier = Modifier.height(5.dp))

                LazyRow() {
                    items(listPaciente.comorbidades.reversed()) {

                        var text = if (listPaciente.comorbidades[0].nome == null){
                            "Não Existe Comorbidades"
                        } else {
                            "${it.nome}"
                        }

                        ProcessingProfile(text = text)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Remédios",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                    CardMedicine()
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }
}

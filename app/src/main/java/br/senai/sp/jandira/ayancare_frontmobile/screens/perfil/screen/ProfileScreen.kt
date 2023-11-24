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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmeUnitariosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmesResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.MedicamentosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Alarme
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeUnitario
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Medicamentos
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
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
    var id = paciente.id
    var foto = paciente.foto

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

    var listAlarmeUnitario by remember {
        mutableStateOf(
            listOf(
                AlarmeUnitario(
                    id_alarme_unitario = 0,
                    id_medicamento = 0,
                    medicamento = "",
                    foto = "",
                    id_alarme = 0,
                    data_criacao = "",
                    intervalo = 0,
                    horario_inicial = "",
                    quantidade_retirada = 0,
                    id_medida = 0,
                    medida = "",
                    medida_sigla = "",
                    status = "",
                    id_paciente = 0,
                    paciente = ""
                )
            )
        )
    }

    var listAlarme by remember {
        mutableStateOf(
            listOf(
                Alarme(
                    paciente = "",
                    id = 0,
                    dia = "",
                    intervalo = 0,
                    horario = "",
                    id_medicamento = 0,
                    medicamento = "",
                    status = ""
                )
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call1 = RetrofitFactory.getAlarme().getAlarmesUnitariosByIdPaciente(id.toInt())

    call1.enqueue(object : Callback<AlarmeUnitariosResponse> {
        override fun onResponse(
            call: Call<AlarmeUnitariosResponse>,
            response: Response<AlarmeUnitariosResponse>
        ) {
            Log.e("listAlarmeUnitario", "onResponse: ${response.body()}")

            if (response.body()!!.status == 404) {
                Log.e("listAlarmeUnitario", "a resposta está nula")
                listAlarmeUnitario = emptyList()
            } else {
                listAlarmeUnitario = response.body()!!.alarme
            }

            Log.e("listAlarmeUnitario", "onResponse: $listAlarmeUnitario")
        }
        override fun onFailure(call: Call<AlarmeUnitariosResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    //Cria uma chamada para o endpoint
    var call2 = RetrofitFactory.getAlarme().getAlarmesByIdPaciente(id.toInt())

    call2.enqueue(object : Callback<AlarmesResponse> {
        override fun onResponse(
            call: Call<AlarmesResponse>,
            response: Response<AlarmesResponse>
        ) {
            Log.e("listAlarme", "onResponse: ${response.body()}")

            if (response.body()!!.status == 404) {
                Log.e("listAlarme", "a resposta está nula")
                listAlarme = emptyList()
            } else {
                listAlarme = response.body()!!.alarme
            }

            Log.e("listAlarme", "onResponse: $listAlarme")
        }
        override fun onFailure(call: Call<AlarmesResponse>, t: Throwable) {
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
                    painter = "$foto"
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

                LazyRow{
                    items(listPaciente.doencas_cronicas.reversed()) {

                        var text = if (listPaciente.doencas_cronicas[0].nome == null){
                            "Não Existe Doenças Crônicas"
                        } else {
                            "${it.nome}"
                        }

                        ProcessingProfile(
                            text = text,
                            width = 200
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

                LazyRow{
                    items(listPaciente.comorbidades.reversed()) {

                        var text = if (listPaciente.comorbidades[0].nome == null){
                            "Não Existe Comorbidades"
                        } else {
                            "${it.nome}"
                        }

                        ProcessingProfile(
                            text = text,
                            width = 200
                        )
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
                    for (remedio in listAlarmeUnitario){
                        CardMedicine(
                            nome = "${remedio.medicamento}",
                            intervalo = remedio.intervalo
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    for (remedio in listAlarme){
                        CardMedicine(
                            nome = "${remedio.medicamento}",
                            intervalo = remedio.intervalo
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
//                    CardMedicine()
//                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }
}

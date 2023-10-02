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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PatientResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Patient
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PatientService
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CardMedicine
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile
import br.senai.sp.jandira.ayancare_frontmobile.viewModel.user.CreateAccountView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

@Composable
fun ProfileScreen(
    navController: NavController,
    navRotasController: NavController,
    viewModel: CreateAccountView
) {

    Log.e("View", "ProfileScreen: ${viewModel.id}")

    val id = viewModel.id

    val scrollState = rememberScrollState()

    // Mantenha uma lista de  patients no estado da tela
    var patients by remember {
        mutableStateOf(
            Patient(
                0, "", "", "", "", "", "", "",
                emptyList(),
                emptyList()
            )
        )
    }

    var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjUsImlhdCI6MTY5NjE5OTI3MiwiZXhwIjoxNjk4NTA3MjcyfQ.IcWG3lmmgr5ppKDYhxWmLdrXyQTy7LKP8Qxbz1xTyHc"

    // Obtém uma instância do Retrofit
    val retrofit = RetrofitFactory.getInstance()

    // Cria uma instância do serviço com a interface definida
    val patientService = retrofit.create(PatientService::class.java)

    // Faz a chamada à API usando a instância do serviço
    val call = patientService.getPatientById(token, 5)



    call.enqueue(object : Callback<PatientResponse> {
        override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
            if (response.isSuccessful) {
                val patientResponse = response.body()
                if (patientResponse != null) {
                    val status = patientResponse.status
                    val patients = patientResponse.paciente
                    Log.d("Status", "Status: $status")
                    Log.d("Patient", "Patient: $patients")

                    // Agora você pode acessar os dados do paciente aqui
                    val nome = patients.nome
                    val email = patients.email
                    // E assim por diante...
                } else {
                    Log.e("API Response", "Resposta vazia")
                }
            } else {
                Log.e("API Response", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
            // Lida com o erro na chamada da API aqui, se necessário
            Log.e("perfil", "Erro na chamada da API: ${t.message}")
        }
    })

    Log.i("TAG", "ProfileScreenssss: ${patients}")

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
                    text = patients.nome,
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
                    text = "Tratamentos",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )

//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = 50.dp),
//            ) {
//                items(6) {
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Card(
//                        modifier = Modifier
//                            .padding(bottom = 10.dp, end = 10.dp),
//                        shape = RoundedCornerShape(size = 16.dp)
//                    ) {
//                        ProcessingProfile()
//                    }
//                }
//            }

                Spacer(modifier = Modifier.height(5.dp))

                LazyRow() {
                    items(patients.comorbidades) {
                        ProcessingProfile(text = it.nome)
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

//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen()
//}
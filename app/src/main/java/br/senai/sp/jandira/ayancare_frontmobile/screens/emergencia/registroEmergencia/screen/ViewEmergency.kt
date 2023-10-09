package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.registroEmergencia.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ResponsavelResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.Responsavel
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ViewEmergencyScreen(
    navController: NavController
) {

    //var phoneNumber by remember { mutableStateOf("966917301") }
    var switchState by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    // Mantenha uma lista de responsáveis no estado da tela
    var listContato by remember {
        mutableStateOf(
            listOf(
                Responsavel(0, "", "", "", 0, 0)
            )
        )
    }

    //Cria uma chamada para o endpoint
    //var call = RetrofitFactory.getResponsible().getResponsavelByPacienteId(idContatoPaciente = id)
    var call = RetrofitFactory.getResponsible().getTodosResponsaveis()

    call.enqueue(object : Callback<ResponsavelResponse> {
        override fun onResponse(
            call: Call<ResponsavelResponse>,
            response: Response<ResponsavelResponse>
        ) {

            Log.e("TAG", "onResponse: ${response.body()}" )
            listContato = response.body()!!.contatos

            Log.e("TAG", "onResponse: $listContato" )
        }
        override fun onFailure(call: Call<ResponsavelResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    Surface(
        color = Color(214, 69, 69)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Ligações de",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center

            )

            Text(
                text = "Emergência",
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        offset = Offset(10f, 10f),
                        blurRadius = 30f
                    )
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            DefaultButton(onClick = { /*TODO*/ }, text = "Adicionar um novo contato")

            LazyColumn(){
                items(5){
                    CustomSwitchWithLabel(
                        text = "Telefone Pessoal",
                        phoneNumber = "11966927301",
                        onSwitchChange = { newState ->
                            switchState = newState
                            if (switchState) {
                                // Inicie a chamada telefônica aqui
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel: 11966927301")
                                context.startActivity(intent)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }
}

@Composable
fun CustomSwitchWithLabel(
    text: String,
    phoneNumber: String,
    onSwitchChange: (Boolean) -> Unit
) {
    var isSwitchOn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                isSwitchOn = !isSwitchOn
                onSwitchChange(isSwitchOn)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = phoneNumber,
                fontSize = 18.sp,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(androidx.compose.material3.MaterialTheme.colorScheme.primary)
        )
    }
}

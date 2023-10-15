package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ResponsavelResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.Responsavel
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.CardResponsible
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.FloatingActionButtonResponsible
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ResponsibleScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    // Mantenha uma lista de responsáveis no estado da tela
    var listResponsavel by remember {
        mutableStateOf(
            listOf(
                Responsavel(0, "", "", "", 0, 0)
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getResponsible().getResponsavelByPacienteId(idContatoPaciente = id)
    //var call = RetrofitFactory.getResponsible().getTodosResponsaveis()

    call.enqueue(object : Callback<ResponsavelResponse> {
        override fun onResponse(
            call: Call<ResponsavelResponse>,
            response: Response<ResponsavelResponse>
        ) {

            Log.e("TAG", "onResponse: ${response.body()}" )
            listResponsavel = response.body()!!.contatos

            Log.e("TAG", "onResponse: $listResponsavel" )
        }
        override fun onFailure(call: Call<ResponsavelResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            //horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navRotasController.navigate("setting_screen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Text(
                    text = "Responsáveis",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF090A0A),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(listResponsavel.reversed()){

                    var text = if (listResponsavel[0].nome == null){
                        "Não Existe Responsáveis no Momento"
                    } else {
                        "${it.nome}"
                    }

                    var numero = if (listResponsavel[0].numero == null){
                        "Não Existe Responsáveis no Momento"
                    } else {
                        "${it.numero}"
                    }


                    var local = if (listResponsavel[0].local == null){
                        "Não Existe Responsáveis no Momento"
                    } else {
                        "${it.local}"
                    }

                    CardResponsible(nome = text, numero = numero, local = local)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
        FloatingActionButtonResponsible(navController)
    }
}
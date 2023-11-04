package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.CardResponsible
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.FloatingActionButtonResponsible
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ResponsibleScreen(
    navController: NavController,
    navRotasController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    var selectedId by remember { mutableIntStateOf(-1) }

    // Mantenha uma lista de responsáveis no estado da tela
    var listResponsible by remember {
        mutableStateOf(
            listOf(
                Responsavel(0, "", "", "", 0, 0)
            )
        )
    }

    //Cria uma chamada para o endpoint
    //var call = RetrofitFactory.getResponsible().getResponsavelByPacienteId(idContatoPaciente = id)
    var call = RetrofitFactory.getResponsible().getTodosResponsaveisByIdPaciente(id = id.toInt())

    call.enqueue(object : Callback<ResponsavelResponse> {
        override fun onResponse(
            call: Call<ResponsavelResponse>,
            response: Response<ResponsavelResponse>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}")

            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listResponsible = emptyList()
            } else {
                listResponsible = response.body()!!.contatos
            }

            Log.e("list-responsible", "onResponse: $listResponsible")
        }
        override fun onFailure(call: Call<ResponsavelResponse>, t: Throwable) {
            Log.i("list-responsible", "onFailure: ${t.message}")
        }

    })

    if (listResponsible.isEmpty()) {
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
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Description,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Não existe nenhum responsável no momento",
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            FloatingActionButtonResponsible(navController)
        }
    } else {
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

                LazyColumn() {
                    items(listResponsible.reversed()) {
                        CardResponsible(
                            id = it.id,
                            nome = it.nome,
                            numero = it.numero,
                            local = it.local,
                            onItemClick = { id ->
                                selectedId = id
                            },
                            localStorage = localStorage,
                            navController
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                localStorage.salvarValor(context, selectedId.toString(), "id_responsible")
                Log.e("list-responsible", "ResponsibleScreen: $selectedId")
            }
            FloatingActionButtonResponsible(navController)
        }
    }
}
package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.registroEmergencia.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Divider
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
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
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ContatoResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.ResponsavelResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.responsible.service.Responsavel
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ViewEmergencyScreen(
    navController: NavController
) {
    var switchState by remember { mutableStateOf(false) }
    var isExclusionMode by remember { mutableStateOf(false) }
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
            Log.e("TAG", "onResponse: ${response.body()}")

            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listResponsavel = emptyList()
            } else {
                listResponsavel = response.body()!!.contatos
            }

            Log.e("TAG", "onResponse: $listResponsavel")
        }
        override fun onFailure(call: Call<ResponsavelResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    if (listResponsavel.isEmpty()) {
        Surface(
            color = Color(214, 69, 69)
        ) {
            IconButton(
                onClick = {
                    navController.navigate("main_screen")
                },
                modifier = Modifier.padding(start = 15.dp, top = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFFFFFFF)
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 10.dp)
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

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate("add_contact_screen")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                    ) {
                        Text(
                            text = "+ Adicionar um novo contato",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFEFE),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

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
                            .size(60.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        text = "Não existe nenhum \ncontato no momento",
                        fontSize = 20.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else {
        Surface(
            color = Color(214, 69, 69)
        ) {
            IconButton(
                onClick = {
                    navController.navigate("main_screen")
                },
                modifier = Modifier.padding(start = 15.dp, top = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFFFFFFF)
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 10.dp)
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

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate("add_contact_screen")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                    ) {
                        Text(
                            text = "+ Adicionar um novo contato",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFEFE),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Botão para ativar/desativar o modo de exclusão
                    Button(
                        onClick = {
                            isExclusionMode = !isExclusionMode
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                    ) {
                        Text(
                            text = if (isExclusionMode) "Cancelar Exclusão" else "Ativar Modo Exclusão",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFEFE),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn{
                    items(listResponsavel) {
                        CustomSwitchWithLabel(
                            text = it.nome,
                            phoneNumber = it.numero,
                            onSwitchChange = { newState ->
                                switchState = newState
                                if (switchState) {
                                    // Inicie a chamada telefônica aqui
                                    val intent = Intent(Intent.ACTION_DIAL)
                                    intent.data = Uri.parse("tel: ${it.numero}")
                                    context.startActivity(intent)
                                }
                            },
                            onDeleteClick = {
                                // Lógica de exclusão aqui
                                // Você pode chamar uma função para excluir o contato
                                // passando o objeto responsavel como parâmetro
                                // Exemplo: onDeleteButtonClick(responsavel)

                                var call = RetrofitFactory.getResponsible().deleteContact(it.id)

                                call.enqueue(object : Callback<ContatoResponse> {
                                    override fun onResponse(
                                        call: Call<ContatoResponse>,
                                        response: Response<ContatoResponse>
                                    ) {
                                        Log.e("deleteContato", "onResponse: ${response.body()}")
                                        navController.navigate("emergencia_screen")
                                    }
                                    override fun onFailure(call: Call<ContatoResponse>, t: Throwable) {
                                        Log.i("deleteContato", "onFailure: ${t.message}")
                                    }
                                })
                            },
                            isExclusionMode = isExclusionMode // Passa o estado do modo de exclusão
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CustomSwitchWithLabel(
    text: String,
    phoneNumber: String,
    onSwitchChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    isExclusionMode: Boolean
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
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (isExclusionMode) {
                // Exibe o ícone de exclusão quando o modo de exclusão está ativado
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir Contato",
                        modifier = Modifier.size(30.dp)
                    )
                }
//                Text(
//                    text = "X",
//                    fontSize = 18.sp,
//                    color = Color(0xFF35225F),
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
            } else {
                // Exibe o número de telefone quando o modo de exclusão está desativado
                Text(
                    text = phoneNumber,
                    fontSize = 18.sp,
                    color = Color(0xFF35225F),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.White)
        )
    }
}

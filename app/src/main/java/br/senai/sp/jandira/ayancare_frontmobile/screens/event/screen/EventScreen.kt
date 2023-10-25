package br.senai.sp.jandira.ayancare_frontmobile.screens.event.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.HeaderEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionDate
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.components.DropdownGender

@Composable
fun EventScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var selecionado by remember { mutableStateOf("evento") }

    val cores = listOf(
        Color(0xFFF7CE46),
        Color(0xFFEF4444),
        Color(0xFFFF8328),
        Color(0xFF10B981),
        Color(0xFF48A7FF),
        Color(0xFF6F00FD)
    )

    var corFundo by remember { mutableStateOf(cores[0]) }

    Surface (
        color = Color(248, 240, 236)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ){
            Row {
                Button(
                    onClick = {
                        navController.navigate("main_screen")
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFE7EAEE))
                ) {
                    Text(
                        text = "Cancelar",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF64748B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp)
                    )

                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        navController.navigate("main_screen")
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF)
                    )
                    //Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Criar Evento",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    )

                }
            }
        }

        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {

            HeaderEvent(
                navController
            )

            Column (
                modifier = Modifier
                    .verticalScroll(scrollState)
            ){
                Text(
                    text = "Título",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF191D23)
                )
                DefaultTextField(
                    valor = "",
                    label = "Nome do Evento",
                    onValueChange = {},
                    aoMudar = {}
                )
                Spacer(modifier = Modifier.height(5.dp))
//                Text(
//                    text = "Tipo",
//                    fontSize = 15.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFF191D23)
//                )
                //DropdownGender()
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Cor",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        cores.forEach { cor ->
                            Card(
                                Modifier
                                    .size(16.dp)
                                    .background(cor, CircleShape)
                                    .clickable {
                                        corFundo = cor
                                    },
                                shape = CircleShape,
                                backgroundColor = cor
                            ) {}
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                    Box (
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                            .background(corFundo, CircleShape),
                    ){}
                }
                Spacer(modifier = Modifier.height(40.dp))

                Row (
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                selecionado = "evento"
                            }
                    ){
                        Text(
                            text = "Evento",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = if (selecionado == "evento") Color.Black else Color(0xFF64748B)
                        )
                        Box (
                            modifier = Modifier
                                .width(155.dp)
                                .height(2.dp)
                                .background(if (selecionado == "evento") Color(0xFF047857) else Color.Transparent),
                        ){}
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                selecionado = "dia"
                            }
                    ){
                        Text(
                            text = "Dia e Horário",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = if (selecionado == "dia") Color.Black else Color(0xFF64748B)
                        )
                        Box (
                            modifier = Modifier
                                .width(155.dp)
                                .height(2.dp)
                                .background(if (selecionado == "dia") Color(0xFF047857) else Color.Transparent)
                        ){}
                    }

                }

                if (selecionado == "evento") {
                    Column () {
                        Spacer(modifier = Modifier.height(40.dp))
                        OptionEvent()
                    }
                } else if (selecionado == "dia") {
                    Column {
                        Spacer(modifier = Modifier.height(40.dp))
                        OptionDate()
                    }
                }

            }
        }
    }

}

//@Preview
//@Composable
//fun aa() {
//    EventScreen()
//}
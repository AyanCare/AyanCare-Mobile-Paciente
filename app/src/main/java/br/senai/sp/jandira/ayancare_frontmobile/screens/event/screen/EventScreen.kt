package br.senai.sp.jandira.ayancare_frontmobile.screens.event.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.ButtonCancel_CreateEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.HeaderEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.finalizarCadastro.components.Dropdown

@Composable
fun EventScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Surface (
        color = Color(248, 240, 236)
    ) {

        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {

            HeaderEvent(
                navController
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column {
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
                Text(
                    text = "Tipo",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF191D23)
                )
                Dropdown()
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.material.Text(
                        text = "Cor",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFFF7CE46),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFFEF4444),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFFFF8328),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFF10B981),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFF48A7FF),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        Modifier
                            .size(16.dp),
                        backgroundColor = Color(0xFF6F00FD),
                        shape = RoundedCornerShape(10.dp)
                    ) {}
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Evento",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF191D23),
                    )
                    Box (
                        modifier = Modifier
                            .width(155.dp)
                            .height(2.dp)
                            .background(color = Color(0xFF047857)),
                    ){}
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Dia e Horário",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF64748B)
                    )
                    Box (
                        modifier = Modifier
                            .width(155.dp)
                            .height(2.dp)
                            .background(color = Color(1, 1, 1, 1)),
                    ){}
                }

            }

            Column (
                modifier = Modifier
                .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                OptionEvent()
            }

            ButtonCancel_CreateEvent(
                navController = NavController(context),
                doneNavController = ""
            )


        }
    }

}

//@Preview
//@Composable
//fun aa() {
//    EventScreen()
//}
package br.senai.sp.jandira.ayancare_frontmobile.screens.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.home.components.CardHome
import br.senai.sp.jandira.ayancare_frontmobile.screens.home.components.HeaderHome
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository

@Composable
fun HomeScreen(
    navRotasController: NavController
) {

    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]

    var nome = paciente.nome
    var genero = paciente.genero

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            //horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 30.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {
            HeaderHome(navRotasController)

            Spacer(modifier = Modifier.height(5.dp))

            Column {
                Text(
                    text =
                    if (genero == "Feminino") {
                        "Bem-Vinda"
                    } else {
                        "Bem-Vindo"
                    },
                    fontSize = 45.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF35225F)
                )
                Text(
                    text = "$nome!",
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF35225F)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "O que deseja ver primeiro?",
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF35225F)
            )

            Spacer(modifier = Modifier.height(14.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                items(1) {
                    CardHome(
                        text = "Verificação de humor",
                        icon = Icons.Default.AddReaction,
                        color = Color.Magenta,
                        color_icon = Color.White,
                        color_text = Color.White,
                        onClick = {
                            navRotasController.navigate("humor_test_screen")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(end = 15.dp, bottom = 90.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            LargeFloatingActionButton(
                onClick = {
                    navRotasController.navigate("emergencia_screen")
                },
                shape = CircleShape,
                containerColor = Color(0xFFE11010)
            ) {
                Icon(
                    imageVector = Icons.Filled.ReportProblem,
                    contentDescription = "Emergencia",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}
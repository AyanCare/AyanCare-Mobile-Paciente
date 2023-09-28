package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.CardResponsible
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components.FloatingActionButtonResponsible

@Composable
fun ResponsibleScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val scrollState = rememberScrollState()

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
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ){
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
                Spacer(modifier = Modifier.height(15.dp))
                CardResponsible()
            }
        }
        FloatingActionButtonResponsible(navController)
    }
}

//@Preview
//@Composable
//fun ResponsibleScreenPreview() {
//    ResponsibleScreen()
//}
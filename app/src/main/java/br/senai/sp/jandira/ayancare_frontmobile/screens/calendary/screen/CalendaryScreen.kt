package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components.ButtonCalendary
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components.Calendary
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components.OptionAlarmCalendary
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components.OptionEventCalendary
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionDate
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionEvent

@Composable
fun CalendaryScreen() {

    var selecionado by remember { mutableStateOf("evento") }

    Surface (
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Calendary()
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    onClick = { selecionado = "evento" },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (selecionado == "evento") Color(0xFF35225F) else Color.White
                    )
                ) {
                    Text(
                        text = "Eventos",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = if (selecionado == "evento") Color.White else Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = { selecionado = "alarme" },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (selecionado == "alarme") Color(0xFF35225F) else Color.White
                    )
                ) {
                    Text(
                        text = "Alarmes",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = if (selecionado == "alarme") Color.White else Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (selecionado == "evento") {
                Column () {
                    Spacer(modifier = Modifier.height(15.dp))
                    OptionEventCalendary()
                }
            } else if (selecionado == "alarme") {
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
                    OptionAlarmCalendary()
                }
            }
        }
    }
}

@Preview
@Composable
fun vsdv() {
    CalendaryScreen()
}
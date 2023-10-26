package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R

@Composable
fun OptionAlarmCalendary() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
    ){
        Text(
            text = "Eventos de hoje",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0xFF35225F)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(){
            items(10){
                CardCalendary()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components.Calendary

@Composable
fun CalendaryScreen() {
    Surface (
        color = Color(248, 240, 236)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Calendary()
        }
    }
}
package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile.R

@Composable
fun CircleProfile(
    painter: Painter
) {
    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier
            .size(100.dp)
            .border(
                BorderStroke(4.dp, Color.White),
                CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun CircleProfilePreview() {
    CircleProfile(painterResource(id = R.drawable.instrucao1))
}
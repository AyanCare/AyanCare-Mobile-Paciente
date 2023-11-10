package br.senai.sp.jandira.ayancare_frontmobile.screens.estoque.verEstoque.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R

@Composable
fun CardEstoque(
    nome_remedio: String,
    quantidade: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .fillMaxWidth()
            .height(80.dp)
            .clickable {
                Log.e("luizão", "CardEstoque: teste-luiz", )
                onClick()
            },
        shape = RoundedCornerShape(size = 4.dp),
        backgroundColor = Color(0xFF35225F)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = nome_remedio,
                fontSize = 22.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF)
            )
            Text(
                text = "Restante: $quantidade unidades",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFFD6C0FF)
            )
        }
    }
}

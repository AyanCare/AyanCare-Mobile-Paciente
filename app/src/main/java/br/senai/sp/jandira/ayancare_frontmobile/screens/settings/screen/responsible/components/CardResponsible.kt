package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.responsible.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestoreFromTrash
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage

@Composable
fun CardResponsible(
    id: Int,
    nome: String,
    numero: String,
    local: String,
    onItemClick: (Int) -> Unit,
    localStorage: Storage,
    navController: NavController
) {

    var isDialogVisibleResponsible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color(0xFF35225F)
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Column {
                Text(
                    text = "Nome: $nome",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.width(300.dp)
                )
                Text(
                    text = "Telefone: $numero",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF)
                )
                Text(
                    text = "Local: \n$local",
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.width(250.dp)
                )
            }
            IconButton(
                onClick = {
                    onItemClick(id)
                    isDialogVisibleResponsible = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp),
                    tint = Color.White
                )
            }
            if (isDialogVisibleResponsible) {
                ModalDeleteResponsable(
                    isDialogVisibleResponsable = false,
                    localStorage = localStorage,
                    navController = navController
                )
            }
        }
    }
}
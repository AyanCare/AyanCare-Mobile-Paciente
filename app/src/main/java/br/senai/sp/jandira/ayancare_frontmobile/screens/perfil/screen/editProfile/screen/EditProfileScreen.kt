package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DateTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.MedicalHistory

@Composable
fun EditProfileScreen(
    //navController: NavController
) {

    val scrollState = rememberScrollState()

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()

                Column(
                    //verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 110.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                        .fillMaxSize()
                ) {
                    CircleProfile(
                        painter = painterResource(id = R.drawable.instrucao3)
                    )

                    DefaultTextField(
                        valor = "",
                        label = "Nome Completo",
                        onValueChange = {},
                        aoMudar = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DefaultTextField(
                        valor = "",
                        label = "CPF",
                        onValueChange = {},
                        aoMudar = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DateTextField()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Doenças Croninas",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF35225F)
                        )
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "",
                                tint = Color(0xFF35225F),
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    }
                    LazyRow() {
                        items(4) {
                            ProcessingProfile()
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Cormobidade",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF35225F)
                        )
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "",
                                tint = Color(0xFF35225F),
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    }
                    LazyRow() {
                        items(4) {
                            ProcessingProfile()
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Histórico Médico",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF35225F)
                        )
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "",
                                tint = Color(0xFF35225F),
                                modifier = Modifier
                                    .size(40.dp)
                            )
                        }
                    }
                    LazyColumn() {
                        items(4) {
                            MedicalHistory()
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                }

        }
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}
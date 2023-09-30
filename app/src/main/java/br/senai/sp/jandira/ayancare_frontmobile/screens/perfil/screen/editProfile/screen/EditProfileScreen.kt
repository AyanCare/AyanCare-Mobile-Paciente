package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DateTextField
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.ProcessingProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.MedicalHistory
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddChronicDiseases
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.ModalAddComorbidity

@Composable
fun EditProfileScreen(
    navController: NavController,
    navRotasController: NavController
) {
    var isDialogVisibleChronicDiseases by remember { mutableStateOf(false) }

    var isDialogVisibleComorbidity by remember { mutableStateOf(false) }

    var nomeCompletoState by remember {
        mutableStateOf("")
    }

    var cpfState by remember {
        mutableStateOf("")
    }


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
            IconButton(
                onClick = {
                    navRotasController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        androidx.compose.material3.Card(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 2.dp,
                                Brush.horizontalGradient(
                                    listOf(
                                        colorResource(id = R.color.purple_200),
                                        Color.White
                                    )
                                )
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google),//painter,
                                contentDescription = "",
                                //colorFilter = ColorFilter.tint(colorResource(id = R.color.pink_login)),
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.baseline_camera_alt_24
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .offset(x = 3.dp, y = 3.dp)
                                .size(30.dp)
                                .clickable {
                                    //launcher.launch("image/*")
                                },
                        )
                    }

                    DefaultTextField(
                        valor = nomeCompletoState,
                        label = "Nome Completo",
                        onValueChange = { nomeCompletoState = it},
                        aoMudar = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DefaultTextField(
                        valor = cpfState,
                        label = "CPF",
                        onValueChange = { cpfState = it },
                        aoMudar = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DateTextField()

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Doenças Crôninas",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleChronicDiseases = true }
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
                if (isDialogVisibleChronicDiseases) {
                    ModalAddChronicDiseases(
                        isDialogVisibleChronicDiseases = false,
                        navController = navController
                    )
                }
                LazyRow() {
                    items(4) {
                        ProcessingProfile()
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cormobidade",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = { isDialogVisibleComorbidity = true }
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
                if (isDialogVisibleComorbidity) {
                    ModalAddComorbidity(
                        isDialogVisibleComorbidity = false,
                        navController = navController
                    )
                }



                LazyRow() {
                    items(4) {
                        ProcessingProfile()
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Histórico Médico",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF35225F)
                    )
                    IconButton(
                        onClick = {}
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
                MedicalHistory()
                Spacer(modifier = Modifier.width(14.dp))
                MedicalHistory()
                Spacer(modifier = Modifier.width(14.dp))
                MedicalHistory()
                Spacer(modifier = Modifier.width(14.dp))
                MedicalHistory()

            }

        }
    }
}

//@Preview
//@Composable
//fun EditProfileScreenPreview() {
//    EditProfileScreen()
//}
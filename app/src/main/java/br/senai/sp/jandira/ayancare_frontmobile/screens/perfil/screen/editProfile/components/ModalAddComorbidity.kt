package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultTextField

@Composable
fun ModalAddComorbidity(
    isDialogVisibleComorbidity: Boolean,
    navController: NavController
) {

    var nomeState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Dialog(
            onDismissRequest = {
                isDialogVisibleComorbidity
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Adicionar \n Comorbidade",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    DefaultTextField(
                        valor = nomeState,
                        label = "Nome",
                        onValueChange = { nomeState = it },
                        aoMudar = {}
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    DefaultButton(
                        onClick = {
                            navController.navigate("edit_profile_screen")
                        },
                        text = "Salvar"
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Cancelar",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("edit_profile_screen")
                            }
                    )
                }
            }
        }
    }
}

package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components

import android.os.Process
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

//ModalAddChronicDiseases

@Composable
fun ModalExampleWithNavigation() {
    var isDialogVisible by remember { mutableStateOf(false) }

    BackHandler(enabled = isDialogVisible) {
        // Intercept the back button when the dialog is visible
        isDialogVisible = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                isDialogVisible = true
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Open Modal")
        }

        if (isDialogVisible) {
            Dialog(
                onDismissRequest = {
                    isDialogVisible = false
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
                            text = "Deseja mesmo sair?",
                            fontSize = 18.sp,
                            //fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    isDialogVisible = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Não",
                                    fontSize = 18.sp,
                                    //fontFamily = FontFamily(Font(R.font.poppins)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFEFE),
                                    textAlign = TextAlign.Center
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Button(
                                onClick = {
                                    isDialogVisible = false
                                    Process.killProcess(Process.myPid())
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Sim",
                                    fontSize = 18.sp,
                                    //fontFamily = FontFamily(Font(R.font.poppins)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFEFE),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ModalExampleWithNavigationPreview() {
    ModalExampleWithNavigation()
}
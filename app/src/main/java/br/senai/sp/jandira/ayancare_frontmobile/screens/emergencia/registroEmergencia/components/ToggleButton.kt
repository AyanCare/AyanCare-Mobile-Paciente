package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.registroEmergencia.components


import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithPhoneNumber(
    //text: String,
    //phoneNumber: String
) {
    var isChecked by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "bla bla bla", //text,
            modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                if (isChecked) {
                    // Iniciar uma chamada telefônica
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel: 1234") //$phoneNumber
                    launcher.launch(intent)
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                checkedTrackColor = Color.Blue,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.Gray
            )
        )
    }
}
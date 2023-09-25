package br.senai.sp.jandira.ayancare_frontmobile.screens.emergencia.adicionarResponsavel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PhoneNumberTextField() {
    var text by remember { mutableStateOf(TextFieldValue()) }

    val formattedText = buildAnnotatedString {
        val phoneNumber = text.text.filter { it.isDigit() }
        val formattedPhoneNumber: String = when {
            phoneNumber.length <= 2 -> "($phoneNumber"
            phoneNumber.length <= 7 -> "(${phoneNumber.substring(0, 2)}) ${phoneNumber.substring(2)}"
            else -> "(${phoneNumber.substring(0, 2)}) ${phoneNumber.substring(2, 7)}-${phoneNumber.substring(7)}"
        }

        withStyle(style = SpanStyle(color = Color.Black)) {
            append(formattedPhoneNumber)
        }
    }

    BasicTextField(
        value = formattedText.toString(),
        onValueChange = {
            val newText = it.filter { char -> char.isDigit() }
            text = TextFieldValue(text = newText)
        },
        textStyle = TextStyle(color = Color.Black),
        modifier = Modifier.background(color = Color.White),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}









@Preview
@Composable
fun PhoneNumberTextFieldte() {
    PhoneNumberTextField()
}
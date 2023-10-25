package br.senai.sp.jandira.ayancare_frontmobile.screens.testeHumor.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Symptoms(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    FilterChip(
        onClick = { onClick() },
        label = {
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = if (selected)Color.White else Color(0xFF35225F)
            )
        },
        shape = RoundedCornerShape(15.dp),
        selected = selected,
        leadingIcon =
        if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = Color.White
                )
            }
        } else {
            null
        },
        colors = FilterChipDefaults.filterChipColors(
            labelColor = Color(0xFF35225F),
            selectedLabelColor = Color(0xFFFFFFFF),
            selectedLeadingIconColor = Color(0xFFFFFFFF),
            selectedContainerColor = Color(0xFF35225F)
        )
    )
}
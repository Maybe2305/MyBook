package com.may.mybook.ui.custom

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.may.mybook.ui.theme.CardColor

@Composable
fun RoundedCornerTextField(
    text: String,
    label: String,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = CardColor,
            unfocusedTextColor = CardColor,
            focusedLabelColor = CardColor,
            unfocusedLabelColor = CardColor,
            focusedBorderColor = CardColor,
            unfocusedBorderColor = CardColor,
        ),
        singleLine = true
    )
}
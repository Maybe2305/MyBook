package com.may.mybook.ui.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.may.mybook.ui.theme.CardColor

@Composable
fun OutlinedButtonCustom(
    text: String,
    color: Color = CardColor,
    onClick: () -> Unit,
) {

    OutlinedButton(
        border = BorderStroke(2.dp, color),
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = color)
    ) {
        Text(text)
    }
}
package com.may.mybook.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.may.mybook.ui.theme.DarkBlue
import com.may.mybook.ui.theme.LightBlue

@Composable
fun RoundedCornerDropDownMenu(
    onOptionSelected: (String) -> Unit,
) {

    val expanded = remember { mutableStateOf(false) }
    val selectedCategory = remember { mutableStateOf("Категория") }
    val categories = listOf(
        "Фэнтези",
        "Драма",
        "Детектив",
        "Фантастика",
        "Приключения",
        "Роман",
        "Юмор",
        "Детские",
        "Учебная"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(1.dp, LightBlue, RoundedCornerShape(4.dp))
            .background(DarkBlue.copy(alpha = 0.6f))
            .clickable { expanded.value = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = selectedCategory.value,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = LightBlue
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            categories.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        selectedCategory.value = option
                        expanded.value = false
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}
package com.may.mybook.model

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.may.mybook.R

sealed class BottomMenuItem(
    val route: String,
    val title: String,
    val iconId: ImageVector,
) {

    object Home : BottomMenuItem(
        route = "home",
        title = "Главная",
        iconId = Icons.Filled.Home
    )

    object Favourite : BottomMenuItem(
        route = "favourite",
        title = "Любимое",
        iconId = Icons.Filled.Favorite
    )

    object Settings : BottomMenuItem(
        route = "settings",
        title = "Настройки",
        iconId = Icons.Filled.Settings
    )
}
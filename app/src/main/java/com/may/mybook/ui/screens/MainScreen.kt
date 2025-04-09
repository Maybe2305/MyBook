package com.may.mybook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.may.mybook.R
import com.may.mybook.ui.theme.DarkBlue
import com.may.mybook.ui.utilities.DrawerBody
import com.may.mybook.ui.utilities.DrawerHeader

@Composable
fun MainScreen() {
    NavigationDrawer()
}

@Composable
fun NavigationDrawer() {
    ModalNavigationDrawer(
        scrimColor = DarkBlue,
        modifier = Modifier.fillMaxWidth(0.7f),
        drawerContent = {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.auth_screen_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.5f
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                DrawerHeader()

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(thickness = 3.dp, color = Color.White)

                DrawerBody()
            }
        }
    ) {

    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
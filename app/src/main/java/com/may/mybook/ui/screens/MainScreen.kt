package com.may.mybook.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.may.mybook.R
import com.may.mybook.data.MainScreenDataObject
import com.may.mybook.ui.theme.DarkBlue
import com.may.mybook.ui.utilities.DrawerBody
import com.may.mybook.ui.utilities.DrawerBottom
import com.may.mybook.ui.utilities.DrawerHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navData: MainScreenDataObject,
    onAddBookClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        NavigationDrawer(
            navData.email,
            onAddBookClick = onAddBookClick
        )
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(
    email: String,
    onAddBookClick: () -> Unit
) {
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {

            Box() {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.7f)
                        .background(DarkBlue),
                    painter = painterResource(R.drawable.auth_screen_bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 0.5f
                )

                Column(
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {


                    DrawerHeader(email)

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider(thickness = 3.dp, color = Color.White)

                    DrawerBody()

                    Spacer(modifier = Modifier.height(200.dp))

                    DrawerBottom(
                        onAddBookClick = { onAddBookClick() }
                    )
                }
            }

        }
    ) {

    }

}

fun signOut() {

}
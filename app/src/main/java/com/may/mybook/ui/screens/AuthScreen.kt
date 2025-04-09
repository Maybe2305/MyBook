package com.may.mybook.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.may.mybook.R
import com.may.mybook.ui.custom.OutlinedButtonCustom
import com.may.mybook.ui.custom.RoundedCornerTextField
import com.may.mybook.ui.theme.BoxFilter
import com.may.mybook.ui.theme.CardColor
import com.may.mybook.ui.theme.CardContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen() {

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(R.drawable.auth_screen_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoxFilter)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.logo_withot_bg),
            contentDescription = null,
            alpha = 0.8f,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.name_app),
            fontFamily = FontFamily.Cursive,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )

        OutlinedCard(
            border = BorderStroke(4.dp, CardColor),
            colors = CardDefaults.cardColors(containerColor = BoxFilter.copy(alpha = 0f))
        ) {
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                RoundedCornerTextField(
                    text = email.value,
                    label = stringResource(R.string.label_for_email),
                    onValueChange = { email.value = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                RoundedCornerTextField(
                    text = password.value,
                    label = stringResource(R.string.label_for_password),
                    onValueChange = { password.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButtonCustom(
                    text = stringResource(R.string.button_sign_in)
                ) { }

                OutlinedButtonCustom(
                    text = stringResource(R.string.button_sign_up)
                ) { }
            }
        }

        Spacer(modifier = Modifier.height(240.dp))
    }
}

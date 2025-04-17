package com.may.mybook.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.may.mybook.R
import com.may.mybook.data.MainScreenDataObject
import com.may.mybook.ui.custom.OutlinedButtonCustom
import com.may.mybook.ui.custom.RoundedCornerTextField
import com.may.mybook.ui.theme.BoxFilter
import com.may.mybook.ui.theme.CardColor

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit
) {

    val emailState = remember {
        mutableStateOf("xamidoff.ar6@gmail.com")
    }

    val passwordState = remember {
        mutableStateOf("upazen23")
    }

    val auth = remember {
        Firebase.auth
    }

    val errorTextState = remember {
        mutableStateOf("")
    }

//    if (auth.currentUser != null) {
//        onNavigateToMainScreen(
//            MainScreenDataObject(
//                uid = auth.currentUser?.uid ?: "",
//                email = auth.currentUser?.email ?: ""
//            )
//        )
//    }

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
                val context = LocalContext.current

                RoundedCornerTextField(
                    text = emailState.value,
                    label = stringResource(R.string.label_for_email),
                    onValueChange = { emailState.value = it },
                )

                Spacer(modifier = Modifier.height(8.dp))

                RoundedCornerTextField(
                    text = passwordState.value,
                    label = stringResource(R.string.label_for_password),
                    onValueChange = { passwordState.value = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (errorTextState.value.isNotBlank()) {
                    Text(
                        modifier = Modifier
                            .background(Color.Black, RoundedCornerShape(14.dp))
                            .padding(8.dp),
                        text = errorTextState.value,
                        color = Color.Red,
                        fontSize = 16.sp,
                    )
                }

                OutlinedButtonCustom(
                    text = stringResource(R.string.button_sign_in)
                ) {
                    signIn(auth = auth,
                        email = emailState.value,
                        password = passwordState.value,
                        onSignInSuccess = { navData ->
                            onNavigateToMainScreen(navData)
                        },
                        onSignInFailure = {
                            errorTextState.value = it
                        })
                }

                OutlinedButtonCustom(
                    text = stringResource(R.string.button_sign_up)
                ) {
                    signUp(auth = auth,
                        email = emailState.value,
                        password = passwordState.value,
                        onSignUpSuccess = { navData ->
                            onNavigateToMainScreen(navData)
                        },
                        onSignUpFailure = {
                            errorTextState.value = it
                        })
                }
            }
        }

        Spacer(modifier = Modifier.height(240.dp))
    }
}

fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: (MainScreenDataObject) -> Unit,
    onSignUpFailure: (String) -> Unit
) {

    if (email.isBlank() || password.isBlank()) {
        onSignUpFailure("Введите почту и пароль")
        return
    }

    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            onSignUpSuccess(
                MainScreenDataObject(
                    uid = it.result.user?.uid ?: "",
                    email = it.result.user?.email ?: "",
                )
            )
        }
    }.addOnFailureListener {
        onSignUpFailure(it.message ?: "Ошибка регистрации")
    }
}

fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignInSuccess: (MainScreenDataObject) -> Unit,
    onSignInFailure: (String) -> Unit
) {

    if (email.isBlank() || password.isBlank()) {
        onSignInFailure("Введите почту и пароль")
        return
    }

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful) {
            onSignInSuccess(
                MainScreenDataObject(
                    uid = it.result.user?.uid ?: "",
                    email = it.result.user?.email ?: "",
                )
            )
        }
    }.addOnFailureListener {
        onSignInFailure(it.message ?: "Ошибка регистрации")
    }
}

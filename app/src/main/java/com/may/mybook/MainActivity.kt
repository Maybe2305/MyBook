package com.may.mybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.may.mybook.data.AddBookScreenObject
import com.may.mybook.data.AuthScreenObject
import com.may.mybook.data.MainScreenDataObject
import com.may.mybook.model.Book
import com.may.mybook.ui.screens.AddBookScreen
import com.may.mybook.ui.screens.AuthScreen
import com.may.mybook.ui.screens.MainScreen
import com.may.mybook.ui.theme.MyBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyBookTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AuthScreenObject,
                ) {
                    composable<AuthScreenObject> {
                        AuthScreen { navData ->
                            navController.navigate(navData)
                        }
                    }

                    composable<MainScreenDataObject> { navEntry ->
                        val navData = navEntry.toRoute<MainScreenDataObject>()
                        MainScreen(
                            navData,
                            onAddBookClick = { navController.navigate(AddBookScreenObject) }
                        )
                    }

                    composable<AddBookScreenObject> {
                        AddBookScreen(
                            onSaved = { navController.popBackStack() },
                            onError = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

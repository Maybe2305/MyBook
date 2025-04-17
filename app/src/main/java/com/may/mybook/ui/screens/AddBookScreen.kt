package com.may.mybook.ui.screens

import android.content.ContentResolver
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.may.mybook.R
import com.may.mybook.model.Book
import com.may.mybook.ui.custom.OutlinedButtonCustom
import com.may.mybook.ui.custom.RoundedCornerDropDownMenu
import com.may.mybook.ui.custom.RoundedCornerTextField
import com.may.mybook.ui.theme.BoxFilter
import com.may.mybook.ui.theme.DarkBlue
import com.may.mybook.ui.theme.LightBlue
import com.google.firebase.ktx.Firebase

@Composable
fun AddBookScreen(
    onSaved: () -> Unit,
    onError: () -> Unit
) {
    val cv = LocalContext.current.contentResolver

    val titleState = remember {
        mutableStateOf("")
    }

    val descriptionState = remember {
        mutableStateOf("")
    }

    val priceState = remember {
        mutableStateOf("")
    }

    var selectedCategory = "Категория"

    val logoUriState = remember {
        mutableStateOf(Uri.EMPTY)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { logoUriState.value = it }
    )

    val firestore = remember {
        Firebase.firestore
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
            .background(DarkBlue.copy(alpha = 0.5f))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        AsyncImage(
            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(8.dp)),
            model = logoUriState.value,
            contentDescription = null,
            alpha = 0.8f,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.name_app),
            fontFamily = FontFamily.Cursive,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = LightBlue
        )

        OutlinedCard(
            border = BorderStroke(4.dp, LightBlue),
            colors = CardDefaults.cardColors(containerColor = BoxFilter.copy(alpha = 0f))
        ) {
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                val context = LocalContext.current

                RoundedCornerTextField(
                    text = titleState.value,
                    label = "Название книги",
                    color = LightBlue,
                    onValueChange = { titleState.value = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                RoundedCornerTextField(
                    text = descriptionState.value,
                    label = "Описание книги",
                    color = LightBlue,
                    singleLine = false,
                    maxLines = 3,
                    onValueChange = { descriptionState.value = it },
                )

                Spacer(modifier = Modifier.height(8.dp))

                RoundedCornerTextField(
                    text = priceState.value,
                    label = "Цена",
                    color = LightBlue,
                    onValueChange = { priceState.value = it },
                )

                Spacer(modifier = Modifier.height(12.dp))

                RoundedCornerDropDownMenu { selectedItem ->
                    selectedCategory = selectedItem
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButtonCustom(
                    text = "Выбрать изображение",
                    color = LightBlue
                ) {
                    launcher.launch("image/*")
                }

                OutlinedButtonCustom(
                    text = "Добавить книгу",
                    color = LightBlue
                ) {
                    saveBookToFireStore(
                        firestore = firestore,
                        book = Book(
                            name = titleState.value,
                            description = descriptionState.value,
                            price = priceState.value,
                            category = selectedCategory,
                            imageUrl = imageToBase64(logoUriState.value, cv)
                        ),
                        onSaved = {  },
                        onError = {  },
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(240.dp))
    }
}

private fun imageToBase64(
    uri: Uri,
    contentResolver: ContentResolver,
) : String {
    val inputStream = contentResolver.openInputStream(uri)
    val bytes = inputStream?.readBytes()

    return bytes?.let {
        Base64.encodeToString(it, Base64.DEFAULT)
    } ?: ""
}

private fun saveBookToFireStore(
    firestore: FirebaseFirestore,
    book: Book,
    onSaved: () -> Unit,
    onError: () -> Unit
) {
    val db = firestore.collection("books")
    val key = db.document().id
    db.document(key)
        .set(
            book.copy(
                key = key,
            )
        )
        .addOnSuccessListener {
            onSaved()
        }
        .addOnFailureListener {
            onError()
        }
}

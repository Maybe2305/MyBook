package com.may.mybook.ui.utilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.may.mybook.R

@Composable
fun DrawerHeader(email: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.logo_withot_bg),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.name_app),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontSize = 32.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = email,
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun DrawerBody() {
    val categories = listOf(
        "Любимые",
        "Фэнтези",
        "Драма",
        "Бестселлеры",
        "Все книги"
    )

    LazyColumn(
        modifier = Modifier,
    ) {

        items(categories) { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  }
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(),
                    text = item,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp, color = Color.White)
            }
        }
    }
}

@Composable
fun DrawerBottom() {
   Row(
       modifier = Modifier.fillMaxWidth().height(48.dp),
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement = Arrangement.SpaceBetween
   ) {
       IconButton(
           modifier = Modifier.weight(1f).background(Color.Black.copy(alpha = 0.3f)),
           onClick = {  }
       ) {
           Icon(
               imageVector = Icons.Filled.Settings,
               contentDescription = null,
           )
       }

       VerticalDivider(thickness = 2.dp, color = Color.White)

       IconButton(
           modifier = Modifier.weight(1f).background(Color.Black.copy(alpha = 0.3f)),
           onClick = {  }
       ) {
           Icon(
               imageVector = Icons.Filled.Add,
               contentDescription = null,
           )
       }

       VerticalDivider(thickness = 2.dp, color = Color.White)

       IconButton(
           modifier = Modifier.weight(1f).background(Color.Black.copy(alpha = 0.3f)),
           onClick = {  }
       ) {
           Icon(
               imageVector = Icons.AutoMirrored.Filled.ExitToApp,
               contentDescription = null,
           )
       }
   }
}

fun isAdmin(
    onAdmin: (Boolean) -> Unit,
) {

}
package com.kost4n.yourmood.screens

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.kost4n.yourmood.R
import com.kost4n.yourmood.navig.Screens
import com.kost4n.yourmood.ui.theme.Purple80

@Composable
fun PrikolScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MediaPlayerView()
        PhotosList2()
    }
}

@Composable
fun PhotosList2() {
    val listImages = listOf<Int>(
        R.drawable.photo1,
        R.drawable.photo12,
        R.drawable.photo16,
        R.drawable.photo14,
        R.drawable.photo9,
        R.drawable.photo6, // взгляд
        R.drawable.photo10,
        R.drawable.photo8,
        R.drawable.photo11,
        R.drawable.photo20, // наркоман
        R.drawable.photo2, // спала сладко сладко
        R.drawable.photo15,
        R.drawable.photo17,
        R.drawable.photo3, // нормальные
        R.drawable.photo4, // я обгашенный
        R.drawable.photo5,
        R.drawable.photo21,
        R.drawable.photo19,
        R.drawable.photo18,
        R.drawable.photo13,
        R.drawable.photo7,
    )

    for (i in listImages) {
        PhotosItem(imageId = i)
    }
}

@Composable
fun PhotosList() {
    val listImages = listOf<Int>(
        R.drawable.photo1,
        R.drawable.photo12,
        R.drawable.photo16,
        R.drawable.photo14,
        R.drawable.photo9,
        R.drawable.photo6, // взгляд
        R.drawable.photo10,
        R.drawable.photo8,
        R.drawable.photo11,
        R.drawable.photo20, // наркоман
        R.drawable.photo2, // спала сладко сладко
        R.drawable.photo15,
        R.drawable.photo17,
        R.drawable.photo3, // нормальные
        R.drawable.photo4, // я обгашенный
        R.drawable.photo5,
        R.drawable.photo21,
        R.drawable.photo19,
        R.drawable.photo18,
        R.drawable.photo13,
        R.drawable.photo7,
        )
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
    ) {
        items(
            items = listImages,
            itemContent = {
                PhotosItem(imageId = it)
            }
        )
    }
}

@Composable
fun PhotosItem(imageId: Int) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(250.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Image(
               bitmap = ImageBitmap.imageResource(id = imageId),
               contentDescription = "Image for us"
           )
            when (imageId) {
                R.drawable.photo6 -> MyText(text = "Мне нравится этот взгяд)")
                R.drawable.photo20 -> MyText(text = "Торчишь?")
                R.drawable.photo2 -> MyText(text = "И спала она сладко сладко")
                R.drawable.photo3 -> MyText(text = "Вот тут оба хорошо получились")
                R.drawable.photo4 -> MyText(text = "А тут я дурачок немного")
                R.drawable.photo21 -> MyText(text = "И не забывай, что ты пиздатая \uD83D\uDE0A")
            }
        }
    }
}

@Composable
fun MyText(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 10.dp)
    )
}

@Composable
fun MediaPlayerView() {
    val mContext = LocalContext.current

    // Declaring and Initializing
    // the MediaPlayer to play "audio.mp3"
    val media = remember { mutableStateOf(R.raw.passmurny) }
    val mMediaPlayer = MediaPlayer.create(mContext, media.value)
    val image = remember { mutableStateOf(R.drawable.image_passmurny) }
    val textImage = remember { mutableStateOf("Сердце - Passmurny") }
    var count = remember { mutableStateOf(0) }
    val listName = listOf(
        "Сердце - Passmurny",
        "Здесь были - Гречка",
        "МАЙ МАЙ - LOV66",
        "К дому - Кепоут",
        "Скучаю - Gruppa Skryptonite",
    )
    val listImages = listOf(
        R.drawable.image_passmurny,
        R.drawable.image_zdes_bili,
        R.drawable.image_maimai,
        R.drawable.image_k_domu,
        R.drawable.image_scuchayu,
    )
    val listMedia = listOf(
        R.raw.passmurny,
        R.raw.zdes_bili,
        R.raw.maimai,
        R.raw.k_domu,
        R.raw.skuchayu
    )
    for (i in listImages) {
        Log.d("images", "$i")
    }
    for (i in listMedia) {
        Log.d("medias", "$i")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = image.value),
            contentDescription = "Image of Music",
            modifier = Modifier
                .size(300.dp, 300.dp)
                .padding(top = 30.dp, start = 10.dp, end = 10.dp)
        )
        Text(
            text = textImage.value,
            modifier = Modifier
                .padding(top = 10.dp),
            fontSize = 18.sp
        )
        Row {
            // IconButton for Pause Action
            IconButton(onClick = { mMediaPlayer.pause() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pause),
                    contentDescription = "",
                    Modifier.size(40.dp)
                )
            }
            // IconButton for Start Action
            IconButton(onClick = { mMediaPlayer.start() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "",
                    Modifier.size(40.dp)
                )
            }
        }
        Button(
            onClick = {
                mMediaPlayer.pause()
                if (count.value == (listMedia.size - 1)) {
                    count.value = 0
                } else {
                    count.value++
                }
                media.value = listMedia[count.value]
                image.value = listImages[count.value]
                textImage.value = listName[count.value]
            },
            modifier = Modifier
                .padding(top = 5.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Purple80,
                contentColor = Color.White,
            ),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Text(text = "Следующую песню", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrikolPreview() {
    PrikolScreen()
}

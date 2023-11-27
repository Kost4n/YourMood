package com.kost4n.yourmood.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kost4n.yourmood.MainActivity
import com.kost4n.yourmood.R
import com.kost4n.yourmood.messaging.messageToKostMail
import com.kost4n.yourmood.navig.Screens
import com.kost4n.yourmood.room.MyViewModel
import com.kost4n.yourmood.room.Rec
import com.kost4n.yourmood.ui.theme.Pink80
import com.kost4n.yourmood.ui.theme.Purple80
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun WriteScreen(
    isPlusPressed: Boolean,
    navController: NavHostController,
    viewModel: MyViewModel,
    activity: MainActivity
) {
    val answerQ1_1 = remember { mutableStateOf(false) }
    val answerQ1_2 = remember { mutableStateOf(false) }
    val answerQ1_3 = remember { mutableStateOf(false) }
    val answerQ1_4 = remember { mutableStateOf(false) }
    val answerQ1_5 = remember { mutableStateOf(false) }
    val answerQ1_6 = remember { mutableStateOf(false) }

    val answerQ2 = remember { mutableStateOf("") }
    val answerQ3 = remember { mutableStateOf(false) }
    val answerQ4 = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp),
        color = Pink80,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            IsPlusPressed(
                isPressed = isPlusPressed,
                answerQ1_1 = answerQ1_1,
                answerQ1_2 = answerQ1_2,
                answerQ1_3 = answerQ1_3,
                answerQ1_4 = answerQ1_4,
                answerQ1_5 = answerQ1_5,
                answerQ1_6 = answerQ1_6,
                answerQ2 = answerQ2,
                answerQ3 = answerQ3,
                answerQ4 = answerQ4,
                navController = navController,
                viewModel = viewModel,
                activity = activity
            )
        }
    }
}

@Composable
fun Questions(
    answerQ1_1: MutableState<Boolean>,
    answerQ1_2: MutableState<Boolean>,
    answerQ1_3: MutableState<Boolean>,
    answerQ1_4: MutableState<Boolean>,
    answerQ1_5: MutableState<Boolean>,
    answerQ1_6: MutableState<Boolean>,
    answerQ2: MutableState<String>,
    answerQ3: MutableState<Boolean>,
    answerQ4: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: MyViewModel,
    activity: MainActivity
) {
    val countQ = remember { mutableStateOf(1) }
    when (countQ.value) {
        1 -> {
            Quest1(
                answerQ1_1 = answerQ1_1,
                answerQ1_2 = answerQ1_2,
                answerQ1_3 = answerQ1_3,
                answerQ1_4 = answerQ1_4,
                answerQ1_5 = answerQ1_5,
                answerQ1_6 = answerQ1_6
            )
        }
        2 -> {
            Quest2(answerQ2 = answerQ2)
        }
        3 -> {
            Quest3(
                answerQ3 = answerQ3,
                answerQ4 = answerQ4
            )
        }
    }
    Button(
        onClick = {
            countQ.value++
            if (countQ.value == 4) {
                navController.navigate(Screens.Hello.route)
                countQ.value = 1
                setResponse(
                    answerQ1_1,
                    answerQ1_2,
                    answerQ1_3,
                    answerQ1_4,
                    answerQ1_5,
                    answerQ1_6,
                    answerQ2,
                    answerQ3,
                    answerQ4,
                    viewModel = viewModel,
                    activity = activity
                )
            }
        },
        modifier = Modifier
            .padding(top = 50.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple80,
            contentColor = Color.White,
        ),
        border = BorderStroke(2.dp, Color.White)
    ) {
        Text(text = "Продолжить", fontSize = 20.sp)
    }
}

fun setResponse(
    answerQ1_1: MutableState<Boolean>,
    answerQ1_2: MutableState<Boolean>,
    answerQ1_3: MutableState<Boolean>,
    answerQ1_4: MutableState<Boolean>,
    answerQ1_5: MutableState<Boolean>,
    answerQ1_6: MutableState<Boolean>,
    answerQ2: MutableState<String>,
    answerQ3: MutableState<Boolean>,
    answerQ4: MutableState<Boolean>,
    viewModel: MyViewModel,
    activity: MainActivity
) {
    var answerQ1 = ""
    if (answerQ1_1.value) {
        answerQ1 = "Отличное \uD83D\uDE0A"
    }
    if (answerQ1_2.value) {
        answerQ1 = "Ну хорошое"
    }
    if (answerQ1_3.value) {
        answerQ1 = "Нормально"
    }
    if (answerQ1_4.value) {
        answerQ1 = "Не очень"
    }
    if (answerQ1_5.value) {
        answerQ1 = "Плохо"
    }
    if (answerQ1_6.value) {
        answerQ1 = "Отъебались все \uD83D\uDE21"
    }

    if (answerQ4.value) {
        var message = answerQ1
        if (answerQ2.value != "") {
            message = "$message - $answerQ2"
        }
        if (answerQ3.value) {
            message += " с этим связан ты"
        }
//        openTelegram(activity, "gritseeva")
//        messageToKost(answer, activity)
        messageToKostMail(message)
    }
    if (answerQ2.value != "") {
        addRecReason(answerQ1, answerQ2.value, viewModel)
    } else {
        addRec(answerQ1, viewModel)
    }
}

fun addRecReason(answerQ1: String, answerQ2: String, viewModel: MyViewModel) {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    viewModel.addRec(Rec(answerQ1, dateFormat.format(Date()), answerQ2))
}

fun addRec(answerQ1: String, viewModel: MyViewModel) {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    viewModel.addRec(Rec(answerQ1, dateFormat.format(Date())))
}

@Composable
fun Quest3(
    answerQ3: MutableState<Boolean>,
    answerQ4: MutableState<Boolean>
) {
    Text(
        modifier = Modifier
            .padding(40.dp)
            .width(300.dp),
        text = "Ответь ещё на пару вопросов",
        fontSize = 20.sp
    )
    MyCheck2(text = "Это как-то связанно со мной?", checkedState = answerQ3)
    MyCheck2(text = "Ты хочешь со мной этим поделиться?", checkedState = answerQ4)
}

@Composable
fun MyCheck2(
    text: String,
    checkedState: MutableState<Boolean>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = text,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(top = 30.dp)
                .width(200.dp)
        )
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {checkedState.value = it},
            modifier = Modifier
                .padding(start = 1.dp)
        )
    }
}


@Composable
fun Quest2(
    answerQ2: MutableState<String>
) {
    Text(
        modifier = Modifier
            .padding(20.dp)
            .width(250.dp),
        text = "Если ты хочешь сказать, что стало причиной, то напиши ниже",
        fontSize = 20.sp
    )
    TextField(
        value = answerQ2.value,
        onValueChange = { newText -> answerQ2.value = newText},
        textStyle = TextStyle(
            fontSize = 20.sp,
            background = Color.White
        ),
        placeholder = { Text(text = "Тут пиши", fontSize = 20.sp, color = Color.Black) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        modifier = Modifier
            .padding(top = 40.dp)
    )
}


@Composable
fun Quest1(
    answerQ1_1: MutableState<Boolean>,
    answerQ1_2: MutableState<Boolean>,
    answerQ1_3: MutableState<Boolean>,
    answerQ1_4: MutableState<Boolean>,
    answerQ1_5: MutableState<Boolean>,
    answerQ1_6: MutableState<Boolean>
) {
    Text(
        modifier = Modifier
            .padding(20.dp),
        text = "Какое у тебя настроение?",
        fontSize = 20.sp
    )
    Row(
        horizontalArrangement = Arrangement.Start
    ){
        Divider(
            modifier = Modifier
                .height(280.dp)
                .width(2.dp),
            color = Color.Black
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            MyCheck(text = "Отличное \uD83D\uDE0A", answerQ1_1)
            MyCheck(text = "Ну хорошое", checkedState = answerQ1_2)
            MyCheck(text = "Нормально \uD83D\uDE10", checkedState = answerQ1_3)
            MyCheck(text = "Не очень", checkedState = answerQ1_4)
            MyCheck(text = "Плохо", checkedState = answerQ1_5)
            MyCheck(text = "Отъебались все \uD83D\uDE21", checkedState = answerQ1_6)
        }
    }
}

@Composable
fun MyCheck(
    text: String,
    checkedState: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp)
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(top = 7.dp)
        )
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {checkedState.value = it},
            modifier = Modifier
                .padding(start = 1.dp)
        )
    }
}

@Composable
fun IsPlusPressed(
    isPressed: Boolean,
    answerQ1_1: MutableState<Boolean>,
    answerQ1_2: MutableState<Boolean>,
    answerQ1_3: MutableState<Boolean>,
    answerQ1_4: MutableState<Boolean>,
    answerQ1_5: MutableState<Boolean>,
    answerQ1_6: MutableState<Boolean>,
    answerQ2: MutableState<String>,
    answerQ3: MutableState<Boolean>,
    answerQ4: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: MyViewModel,
    activity: MainActivity
) {
    if (isPressed) {
        Questions(
            answerQ1_1 = answerQ1_1,
            answerQ1_2 = answerQ1_2,
            answerQ1_3 = answerQ1_3,
            answerQ1_4 = answerQ1_4,
            answerQ1_5 = answerQ1_5,
            answerQ1_6 = answerQ1_6,
            answerQ2 = answerQ2,
            answerQ3 = answerQ3,
            answerQ4 = answerQ4,
            navController = navController,
            viewModel = viewModel,
            activity = activity
        )
    } else {
        WelcomeText(navController)
    }
}

@Composable
fun WelcomeText(navController: NavHostController) {
    Text(
        modifier = Modifier
            .width(270.dp)
            .padding(top = 50.dp),
        text = "Крч, это приложение я решил сделать как подарочек",
        fontSize = 18.sp
    )
//    Text(
//        modifier = Modifier
//            .width(260.dp)
//            .padding(top = 10.dp),
//        text = "Я даже Ленку спрашивал, что тебе может понравится",
//        fontSize = 14.sp
//    )
    Text(
        modifier = Modifier
            .width(270.dp)
            .padding(top = 10.dp),
        text = "В чём суть? Тут ты будешь записывать своё настроение " +
            "и по твоему усмотрению мне будет отсылаться данные по настроению. В этом случае," +
            " я могу что-то для тебя сделать)",
        fontSize = 18.sp
    )
    Text(
        modifier = Modifier
            .width(270.dp)
            .padding(top = 15.dp),
        text = "Чтобы записать настроение нажми на \"+\" внизу",
        fontSize = 18.sp
    )

    Image(
        bitmap = ImageBitmap.imageResource(id = R.drawable.photo_special),
        contentDescription = "",
        modifier = Modifier
            .size(240.dp)
            .padding(top = 10.dp)
    )
    Text(
        modifier = Modifier
            .width(270.dp),
        text = "А ещё перейди по кнопке, думаю тебе понравится",
        fontSize = 18.sp
    )
    Button(
        onClick = {
            navController.navigate(Screens.Prikol.route)
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
        Text(text = "Вот по этой кнопке", fontSize = 20.sp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WritePreview() {
//    WriteScreen()
//}


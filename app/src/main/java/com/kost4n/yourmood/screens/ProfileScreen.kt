package com.kost4n.yourmood.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.kost4n.yourmood.MainActivity
import com.kost4n.yourmood.room.MyViewModel
import com.kost4n.yourmood.room.Rec

@Composable
fun ProfileScreen(
    viewModel: MyViewModel,
    context: MainActivity
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PresentText()

        MyRecyclerView(
            viewModel = viewModel,
            context = context
        )
    }
}

@Composable
fun MyRecyclerView(viewModel: MyViewModel, context: MainActivity) {
    val listRecs = remember { viewModel.getRecs() }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
    ) {
        listRecs.observe(context, Observer {
            if (listRecs.value != null) {
                items(
                    items = listRecs.value!!,
                    itemContent = {
                        RecItem(rec = it)
                    }
                )
            }
        })
    }
}

@Composable
fun RecItem(rec: Rec) {
    val openDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(220.dp)
            .clickable {
                openDialog.value = true
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = rec.date,
                fontSize = 16.sp,
            )
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .width(100.dp),
                thickness = 2.dp,
                color = Color.Black
            )
            Text(
                text = rec.mood,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = rec.date)
            },
            text = {
                if (rec.reason != "") {
                    Text("Настроение: ${rec.mood} \nПричина: ${rec.reason}")
                } else {
                    Text("Настроение: ${rec.mood}")
                }
            },
            confirmButton = {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { openDialog.value = false }
                ) {
                    Text("Ok")
                }
            }
        )
    }
}

@Composable
fun PresentText() {
    Text(
        text = "Тут ты можешь просмотреть какие у тебя были настроения",
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 25.dp)
            .width(275.dp)
    )
}
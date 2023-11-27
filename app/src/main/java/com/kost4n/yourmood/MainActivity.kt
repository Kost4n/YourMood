package com.kost4n.yourmood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.kost4n.yourmood.navig.BottomNavigationBar
import com.kost4n.yourmood.navig.NavGraph
import com.kost4n.yourmood.navig.Screens
import com.kost4n.yourmood.room.MyViewModel
import com.kost4n.yourmood.ui.theme.Pink80
import com.kost4n.yourmood.ui.theme.Purple80
import com.kost4n.yourmood.ui.theme.YourMoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourMoodTheme {
                Scaf()
            }
        }
    }

    private fun getViewModel() = ViewModelProvider(this@MainActivity)[MyViewModel::class.java]


    @Preview(showBackground = true)
    @Composable
    private fun Scaf() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Pink80,
        ) {
            val navController = rememberNavController()
            Scaffold(
                backgroundColor = Pink80,
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                },
                floatingActionButtonPosition = FabPosition.Center,
                isFloatingActionButtonDocked = true,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screens.Write.route)
                        },
                        backgroundColor = Purple80
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                },
                content = { padding ->
                    NavGraph(navController = navController, padding = padding,
                        viewModel = getViewModel(), context = this)
                }
            )
        }
    }
}


package com.kost4n.yourmood.navig

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.composable
import com.kost4n.yourmood.MainActivity
import com.kost4n.yourmood.room.MyViewModel
import com.kost4n.yourmood.screens.PrikolScreen
import com.kost4n.yourmood.screens.ProfileScreen
import com.kost4n.yourmood.screens.WriteScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: MyViewModel,
    context: MainActivity
){
    NavHost (
        navController = navController,
        startDestination = Screens.Hello.route,
        modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable(route = Screens.Hello.route) {
            WriteScreen(
                isPlusPressed = false,
                navController = navController,
                viewModel = viewModel,
                activity = context

            )
        }
        composable(route = Screens.Write.route) {
            WriteScreen(
                isPlusPressed = true,
                navController = navController,
                viewModel = viewModel,
                activity = context
            ) /* TODO add isPressed to arguments*/
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(
                viewModel = viewModel,
                context = context
            )
        }
        composable(route = Screens.Prikol.route) {
            PrikolScreen()
        }
    }
}
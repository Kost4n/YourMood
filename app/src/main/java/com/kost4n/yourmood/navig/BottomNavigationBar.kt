package com.kost4n.yourmood.navig

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kost4n.yourmood.ui.theme.Purple80

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier.height(70.dp),
        backgroundColor = Purple80,
        cutoutShape = CircleShape
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val views = listOf(
            Screens.Hello,
            Screens.Profile
        )
        views.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(
                        bitmap = ImageBitmap.imageResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        modifier = Modifier.size(30.dp, 30.dp),
                    )
                },
                label = {
                },
                alwaysShowLabel = false
            )
        }
    }
}
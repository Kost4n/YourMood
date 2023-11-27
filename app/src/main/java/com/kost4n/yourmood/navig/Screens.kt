package com.kost4n.yourmood.navig

import com.kost4n.yourmood.R

sealed class Screens(
    val label: String,
    val icon: Int,
    val route:String,
) {
    object Hello: Screens(
        label = "Hello",
        icon = R.drawable.icon_write,
        route = "hello_screen"
    )
    object Write: Screens(
        label = "Write",
        icon = R.drawable.icon_write,
        route = "write_screen"
    )
    object Profile: Screens(
        label = "Profile",
        icon = R.drawable.icon_profile,
        route = "profile_screen"
    )
    object Prikol: Screens(
        label = "Prikol",
        icon = R.drawable.icon_profile,
        route = "prikol_screen"
    )
}



package com.mbialowas.beersonandroid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val route:String,
    val icon:ImageVector,
    val title: String
    ) {

    object Beers: BottomNavItems(
        "beers",
        icon = Icons.Outlined.Home,
        "Beers"
    )
    object Like: BottomNavItems(
        "like",
        icon = Icons.Outlined.Check,
        "Like"
    )
    object Profile: BottomNavItems(
        "profile",
        icon = Icons.Outlined.Menu,
        "Profile"
    )

}
package com.mbialowas.beersonandroid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBar (
    val route: String,
    val title: String,
    val icon: ImageVector
)
{
    object Home : BottomNavBar(
        route = "beers",
        title = "Beers",
        icon = Icons.Default.Home
    )

    object Likes : BottomNavBar(
        route = "likes",
        title = "Likes",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomNavBar(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Settings
//        icon = Icons.Default.Info
    )

    companion object {
        fun values() = listOf(Home, Likes, Profile)
    }
}
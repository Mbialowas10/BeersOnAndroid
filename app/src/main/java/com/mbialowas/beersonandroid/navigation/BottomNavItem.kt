package com.mbialowas.beersonandroid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        "home", "Home", Icons.Default.Home
    )
    object About : BottomNavItem(
        "about", "About", Icons.Default.Info
    )
}
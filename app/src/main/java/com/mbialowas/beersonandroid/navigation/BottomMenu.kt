package com.mbialowas.beersonandroid.navigation

import androidx.compose.runtime.Composable

@Composable
fun BottomMenu(){
    val menuItems = listOf(
        BottomNavItems.Beers,
        BottomNavItems.Like,
        BottomNavItems.Profile
    )

}

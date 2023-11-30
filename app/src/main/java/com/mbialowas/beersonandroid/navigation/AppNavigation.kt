package com.mbialowas.beersonandroid.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseUser
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.screens.About
import com.mbialowas.beersonandroid.screens.Beers
import com.mbialowas.beersonandroid.screens.FavoriteScreen
import com.mbialowas.beersonandroid.screens.LoginScreen
import com.mbialowas.beersonandroid.screens.SignUpScreen

// Function to define navigation routes
@Composable
fun AppNavigation(navController: NavHostController, beersManager: BeersManager) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register"){
            SignUpScreen(navController = navController)
        }

        composable(BottomNavItem.Home.route) {
            Beers(beersManager = beersManager, navController)
        }

        composable(BottomNavItem.Favorite.route) {
            FavoriteScreen(beersManager = beersManager, navController)
        }

        composable(BottomNavItem.About.route) {
            About(navController)
        }
    }
}
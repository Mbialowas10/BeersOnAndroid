package com.mbialowas.beersonandroid.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mbialowas.beersonandroid.api.BeersManager

@Composable
fun BeerApp(beersManager: BeersManager){

    // list of beers on home page
    //Beers(BeersManager())
    Navigation(beersManager)
}

@Composable
fun Navigation(beersManager: BeersManager){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "beers"
    ) {
        composable("beers") {
            Beers(beersManager = beersManager,navController)
        }
        composable("about") {
            About(navController)
        }


    }
}



package com.mbialowas.beersonandroid.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mbialowas.beersonandroid.api.BeersManager



@Composable
fun BeerApp(beersManager: BeersManager,user: FirebaseUser){

    // list of beers on home page
    //Beers(BeersManager())
    Navigation(beersManager,user)

}



@Composable
fun Navigation(beersManager: BeersManager,user: FirebaseUser){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "beers"
    ) {

        composable("beers") {
            Beers(beersManager = beersManager, navController)
        }
        composable("about") {
            About(navController)
        }

    }
    //BottomNav(navController,beersManager)

}




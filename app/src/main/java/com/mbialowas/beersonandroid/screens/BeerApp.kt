package com.mbialowas.beersonandroid.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.mbialowas.beersonandroid.api.BeersManager



@Composable
fun BeerApp(beersManager: BeersManager,auth: FirebaseAuth){

    // list of beers on home page
    //Beers(BeersManager())
    Navigation(beersManager,auth)

}



@Composable
fun Navigation(beersManager: BeersManager,auth: FirebaseAuth){
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
    //BottomNav(navController,beersManager)

}




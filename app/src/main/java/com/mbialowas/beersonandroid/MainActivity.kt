package com.mbialowas.beersonandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.navigation.BottomNavBar
import com.mbialowas.beersonandroid.navigation.BottomNavItem
import com.mbialowas.beersonandroid.screens.About


import com.mbialowas.beersonandroid.screens.BeerApp
import com.mbialowas.beersonandroid.screens.Beers
import com.mbialowas.beersonandroid.screens.FavoriteScreen
import com.mbialowas.beersonandroid.screens.LoginScreen

import com.mbialowas.beersonandroid.ui.theme.BeersOnAndroidTheme

class MainActivity : ComponentActivity() {
    // api source --> https://sampleapis.com/api-list/beers
    private var auth: FirebaseAuth? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeersOnAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // fetches our beers from api when class is initialized
                    val beersManager: BeersManager = BeersManager()


                    val navController = rememberNavController()

                    // define routes above to avoid error
                    NavHost(navController, startDestination = "login") {
                        composable("login"){
                            auth?.let { it1 -> LoginScreen(auth = it1, navController = navController) }
                        }

                        composable(BottomNavItem.Home.route) {
                            // Replace this with your 'Home' composable content
                            //Text("Home Screen")
                            Beers(beersManager = beersManager, navController)
                        }
                        composable(BottomNavItem.Favorite.route) {
                            // Replace this with your 'Home' composable content
                            //Text("Home Screen")
                            FavoriteScreen(beersManager = beersManager, navController)
                        }
                        composable(BottomNavItem.About.route) {
                            // Replace this with your 'About' composable content
                            //Text("About Screen")
                            About(navController)
                        }
                    }

                    // authenticate user
                    val user = auth?.currentUser
                    if (user == null){
                        // load SignIn Composable
                        navController.navigate("login")

                    }else {
                        // user logged in, load content as you normally would
                        auth?.let { BeerApp(beersManager, it) } // load the beer list
                        // begin scaffold
                        Scaffold(
                            bottomBar = { BottomNavBar(navController) }
                        ) {

                        }
                    }
                }
            }
        }
    }
}


package com.mbialowas.beersonandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.navigation.AppNavigation
import com.mbialowas.beersonandroid.navigation.BottomNavBar


import com.mbialowas.beersonandroid.ui.theme.BeersOnAndroidTheme

class MainActivity : ComponentActivity() {
    // api source --> https://sampleapis.com/api-list/beers
    private var auth: FirebaseAuth? = null // ref to firebase auth

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
                    // try to see if user logged in
                    val auth = FirebaseAuth.getInstance()
                    val user = auth?.currentUser

                    AppNavigation(navController = navController, beersManager = beersManager)

                    if (user == null) {
                        // redirect user to login form
                        navController.navigate("login")
                    }

                    Scaffold(
                        bottomBar = { BottomNavBar(navController) }
                    ) {
                        AppNavigation(navController = navController, beersManager = beersManager)
                    }
                }
            }
        }
    }
}


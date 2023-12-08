package com.mbialowas.beersonandroid.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun About(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment= Alignment.CenterHorizontally
        ){
            Text(text="About")
            Text(text="Beers On Android Demo")
            Button(onClick = {
                // use passed in navController to navigate to another page
                // this is not great because all our screens get
                // added to stack, demo this by press back button repeatedly
                navController.navigate("beers")
                // fix this by doing
                // close current screen and go back
                //navController.popBackStack()
            }) {
                Text(text="Back")
            }
        }
    }
}
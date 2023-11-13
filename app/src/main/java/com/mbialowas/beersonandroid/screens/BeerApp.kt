package com.mbialowas.beersonandroid.screens

import androidx.compose.runtime.Composable
import com.mbialowas.beersonandroid.api.BeersManager

@Composable
fun BeerApp(beersManager: BeersManager){

    // list of beers on home page
    Beers(BeersManager())
}


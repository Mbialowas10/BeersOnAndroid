package com.mbialowas.beersonandroid.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.db.FireStoreInstance
import com.mbialowas.beersonandroid.model.BeerItem

@Composable
fun FavoriteScreen(beersManager: BeersManager,navController: NavController) {

    // Mutable state to hold the list of beer items fetched from Firestore
    val beerItems = remember { mutableStateOf(listOf<BeerItem>()) }
    val fsInstance = FireStoreInstance.getInstance()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text="Likes Screen",
            modifier = Modifier.padding(0.dp, 7.dp),
            fontSize = 25.sp
        )
    }


    // Function to fetch beer items from Firestore
    fun fetchBeerItems() {
        fsInstance.collection("favorites")
            .get()
            .addOnSuccessListener { documents ->
                val items = mutableListOf<BeerItem>()
                for (document in documents) {
                    // Convert Firestore document to BeerItem model
                    val beerItem = document.toObject(BeerItem::class.java)
                    items.add(beerItem)
                }
                // Update the mutable state with fetched beer items
                beerItems.value = items
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }
    // Fetch beer items when this Composable gets launched
    LaunchedEffect(Unit) {
        fetchBeerItems()
    }
    // Display fetched beer items in a LazyColumn
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(beerItems.value) { beerItem ->
            BeerCard(beerItem = beerItem,navController)
        }
    }


}
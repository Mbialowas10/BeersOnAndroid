package com.mbialowas.beersonandroid.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.db.FireStoreInstance
import com.mbialowas.beersonandroid.model.BeerItem
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun Beers(beersManager: BeersManager, navController: NavController){
    val beers = beersManager.beersResponse.value
    Log.d("beers", "$beers")
    val name:String
    val image:String
    val id:String
    val price:String

    for (beer in beers){
        Log.i("name", "${beer.rating}")
    }
    Column() {
        Row{
            Button(onClick = {
                // use passed in navController to navigate to another page
                navController.navigate("about")
            }) {
                Text(text="About App Section")
            }
            Button(
                onClick = {
                    //auth.signOut()
                    navController.navigate("login")
                },
                modifier = Modifier.padding(8.dp)
            ){
                // check auth and provide signout button
                Text(text="Sign out Now")
            }
        }

        LazyColumn{
            items(beers){beer->
                BeerCard(beerItem = beer, navController)
            }
        }
    }




//    Column {
//        BeerCard(name="Bud Light")
//
//        BeerCard(name="Geeko")
//        BeerCard(name="Mike")
//    }



}
@Composable
fun BeerCard(
    beerItem: BeerItem,
    navController: NavController

){
    var isIconChanged by remember { mutableStateOf(false) }
    val fsInstance = FireStoreInstance.getInstance()

    /* Maintain a list to store added BeerItems with their DocumentReferences
    val addedBeerItems: MutableList<BeerItemWithDocRef> = mutableListOf()
    */


    Column(modifier = Modifier

        .border(1.dp, Color.Red, shape = RectangleShape)
        .padding(5.dp)
        //.size(60.dp)


    ) {

        Row(
            modifier = Modifier
                //.border(2.dp, Color.Black, shape = CircleShape)
                .background(color = Color.DarkGray)
                .fillMaxWidth()
                //.height(80.dp)
                .padding(5.dp)
        ){
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                //painter = painterResource(id = R.drawable.bluemoon),
                painter = rememberImagePainter(data=beerItem.image),
                contentDescription = "Beer",

                )
            Column {
                Text(
                    color = Color.White,
                    text = beerItem.name,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp),
                    style = TextStyle(fontSize = 24.sp),
                    maxLines = 1
                ) // end Text
                Text(
                    text = beerItem.price,
                    modifier = Modifier.padding(end = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Column {
                    Row {
                        Text(
                            text = "Average Vote: " + BigDecimal(beerItem.rating.average).setScale(2,
                                RoundingMode.HALF_UP).toString() + "/5",
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Text(
                            text = "# of Reviews " + beerItem.rating.reviews.toString(),
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Button(
                            onClick = {
                                isIconChanged = !isIconChanged

                                // get current user and link farorites to that user
                                val user = Firebase.auth.currentUser

                                //val beerDocRef = fsInstance.collection("favorites").document(beerItem.id.toString())
                                val beerDocRef = fsInstance.collection("users").document(user?.email.toString()).collection("farvoites")
                                    .document(beerItem.id.toString())

                                if (isIconChanged) {
                                    // Add beer item to Firestore favorites collection
                                    beerDocRef.set(beerItem)
                                        .addOnSuccessListener {
                                            // Success message or further action if needed
                                            // Toast, Snackbar, etc.
                                            Log.d("MJB", "Inserted ${beerItem.name}")
                                        }
                                        .addOnFailureListener { e ->
                                            // Handle failure
                                            Log.d("Error", "${e.message}")
                                        }
                                } else {
                                    // Remove beer item from Firestore favorites collection
                                    beerDocRef.delete()
                                        .addOnSuccessListener {
                                            // Success message or further action if needed
                                            // Toast, Snackbar, etc.
                                            Log.d("MJB", "Deleted ${beerItem.name}")
                                        }
                                        .addOnFailureListener { e ->
                                            // Handle failure
                                            Log.d("Error", "${e.message}")
                                        }
                                }


                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .scale(2.5f),
                                imageVector = if (isIconChanged) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Add a favorite"
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
} // end BeerCard Composable



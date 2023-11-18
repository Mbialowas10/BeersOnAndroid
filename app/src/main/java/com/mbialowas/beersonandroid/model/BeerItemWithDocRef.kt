package com.mbialowas.beersonandroid.model

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.mbialowas.beersonandroid.db.FireStoreInstance

// Class to hold BeerItem and its DocumentReference
data class BeerItemWithDocRef(
    val beerItem: BeerItem,
    val documentReference: DocumentReference
)

// Maintain a list to store added BeerItems with their DocumentReferences
val addedBeerItems: MutableList<BeerItemWithDocRef> = mutableListOf()

// Function to add a BeerItem to Firestore
fun addBeerItemToFirestore(beerItem: BeerItem) {
    // get fireBaseFirestore instance
    val db = FireStoreInstance.getInstance()

    val beerCollection = db.collection("beers") // Name of your Firestore collection

    // Convert BeerItem to a Map
    val beerMap = mapOf(
        "id" to beerItem.id,
        "image" to beerItem.image,
        "name" to beerItem.name,
        "price" to beerItem.price,
        "rating" to mapOf(  // Assuming Rating is another data class
            "average" to beerItem.rating.average  // Map fields of the Rating class
        )
    )

    // Add the beerMap to the Firestore collection
    beerCollection
        .add(beerMap)
        .addOnSuccessListener { documentReference ->
            // Successfully added
            Log.d("Firebase","DocumentSnapshot written with ID: ${documentReference.id}")

            // Store the BeerItem with its DocumentReference
            val beerItemWithReference = BeerItemWithDocRef(beerItem, documentReference)
            addedBeerItems.add(beerItemWithReference)
        }
        .addOnFailureListener { e ->
            // Failed to add
            Log.d("Firebase","Error adding document: $e")
        }
}

// Function to remove BeerItem from Firestore and the list
fun removeBeerItemFromFirestore(beerItem: BeerItem) {
    val itemToRemove = addedBeerItems.find { it.beerItem == beerItem }
    itemToRemove?.let { item ->
        item.documentReference.delete()
            .addOnSuccessListener {
                // Document successfully deleted from Firestore

                // Remove the BeerItem from the list
                addedBeerItems.remove(item)
                Log.d("Firebase", "Document successfully deleted!")
            }
            .addOnFailureListener { e ->
                // Handle any errors
                Log.d("Firebase", "Error deleting document: $e")
            }
    }
}
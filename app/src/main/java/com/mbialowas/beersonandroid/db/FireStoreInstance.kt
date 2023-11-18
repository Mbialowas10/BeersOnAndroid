package com.mbialowas.beersonandroid.db

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mbialowas.beersonandroid.model.BeerItem

object FireStoreInstance {
    private var instance: FirebaseFirestore? = null

    fun getInstance(): FirebaseFirestore {
        synchronized(this) {
            if (instance == null) {
                instance = FirebaseFirestore.getInstance()
                // You can also configure Firestore settings here if needed
            }
        }
        return instance!!
    }



}

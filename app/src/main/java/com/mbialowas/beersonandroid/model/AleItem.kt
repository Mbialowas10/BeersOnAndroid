package com.mbialowas.beersonandroid.model


import com.google.gson.annotations.SerializedName
import com.mbialowas.beersonandroid.Rating


data class AleItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image_url: String, //rename image to image_url for internal use
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rating")
    val rating: Rating
)
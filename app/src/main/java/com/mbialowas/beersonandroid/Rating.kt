package com.mbialowas.beersonandroid


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    val average: Double,
    @SerializedName("reviews")
    val reviews: Int
)
package com.mbialowas.beersonandroid.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    @Json(name = "average")
    val average: Double,
    @Json(name = "reviews")
    val reviews: Int
){
    // Default (no-argument) constructor
    // default constructors need to avoid the following error
    // Rating does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped (found in field 'rating')
    constructor() : this(0.0, 0)
}
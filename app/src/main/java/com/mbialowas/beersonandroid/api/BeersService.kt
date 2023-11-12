package com.mbialowas.beersonandroid.api


import com.mbialowas.beersonandroid.model.BeerItem
import com.mbialowas.beersonandroid.model.BeerList
import retrofit2.Call
import retrofit2.http.GET

interface BeersService {
    @GET("beers/ale")
    fun getBeers(): Call<List<BeerItem>>
}
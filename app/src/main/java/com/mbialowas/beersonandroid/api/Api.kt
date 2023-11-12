package com.mbialowas.beersonandroid.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    private val BASE_URL = "https://api.sampleapis.com/"


    // convert json into objects that project code can understand
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //initialize retrofit
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build() // now able to use it

    val retrofitService: BeersService by lazy {
        retrofit.create(BeersService::class.java)
    }
}
package com.example.a3200finalproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Use Retrofit to create an instance of MetApiService
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://collectionapi.metmuseum.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val metApiService: MetApiService = retrofit.create(MetApiService::class.java)
}
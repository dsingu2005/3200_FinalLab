package com.example.a3200finalproject

import retrofit2.http.GET
import retrofit2.http.Path

interface MetApiService {
    @GET("public/collection/v1/objects/{objectID}")
    suspend fun getArtObject(@Path("objectID") objectID: Int): ArtObject
}
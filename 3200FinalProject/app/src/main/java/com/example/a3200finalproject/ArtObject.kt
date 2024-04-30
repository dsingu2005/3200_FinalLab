package com.example.a3200finalproject

import com.google.gson.annotations.SerializedName

data class ArtObject(
    @SerializedName("objectID") val objectID: Int,
    @SerializedName("primaryImage") val primaryImage: String,
    @SerializedName("title") val title: String
)
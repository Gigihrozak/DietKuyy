package com.example.dietkuyy


import com.google.gson.annotations.SerializedName

data class FoodPosItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("nama")
    val nama: String
)
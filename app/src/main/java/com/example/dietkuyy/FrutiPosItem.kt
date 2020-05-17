package com.example.dietkuyy


import com.google.gson.annotations.SerializedName

data class FrutiPosItem(
    @SerializedName("calori")
    val calori: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("nama")
    val nama: String
)
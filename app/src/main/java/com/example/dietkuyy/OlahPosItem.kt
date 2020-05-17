package com.example.dietkuyy


import com.google.gson.annotations.SerializedName

data class OlahPosItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("kal")
    val kal: String,
    @SerializedName("nama")
    val nama: String
)
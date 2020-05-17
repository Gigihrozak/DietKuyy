package com.example.dietkuyy.data
import com.example.dietkuyy.FoodPosItem
import retrofit2.Call
import retrofit2.http.GET
interface FoodService {
    @GET("makan/posts")
    fun getposts(): Call<List<FoodPosItem>>
}
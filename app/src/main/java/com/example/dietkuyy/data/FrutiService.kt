package com.example.dietkuyy.data
import com.example.dietkuyy.FrutiPosItem
import retrofit2.Call
import retrofit2.http.GET
interface FrutiService {
    @GET("buah/fruit")
    fun getfruit(): Call<List<FrutiPosItem>>
}
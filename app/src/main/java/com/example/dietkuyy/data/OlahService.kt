package com.example.dietkuyy.data
import com.example.dietkuyy.OlahPosItem
import retrofit2.Call
import retrofit2.http.GET
interface OlahService {
    @GET("olah/sehat")
    fun getsehat(): Call<List<OlahPosItem>>
}
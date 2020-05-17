package com.example.dietkuyy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietkuyy.data.FoodService
import com.example.dietkuyy.data.apiRequest
import com.example.dietkuyy.data.httpClient
import com.example.dietkuyy.util.dismissLoading
import com.example.dietkuyy.util.showLoading
import com.example.dietkuyy.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_food.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class Food : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetFoodUser()
    }
    private fun callApiGetFoodUser() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<FoodService>(httpClient)
        val call = apiRequest.getposts()
        call.enqueue(object : Callback<List<FoodPosItem>> {
            override fun onFailure(call: Call<List<FoodPosItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<FoodPosItem>>, response:
            Response<List<FoodPosItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilFoodUser(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilFoodUser(foodUsers: List<FoodPosItem>) {
        listGithubUser.layoutManager = LinearLayoutManager(context)
        listGithubUser.adapter = FoodPosAdapter(context!!, foodUsers) {
            val foodUser = it
            tampilToast(context!!, foodUser.id)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}

package com.example.dietkuyy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietkuyy.data.FoodService
import com.example.dietkuyy.data.OlahService
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
class List_Day : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list__day, container, false)
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
        val apiRequest = apiRequest<OlahService>(httpClient)
        val call = apiRequest.getsehat()
        call.enqueue(object : Callback<List<OlahPosItem>> {
            override fun onFailure(call: Call<List<OlahPosItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<OlahPosItem>>, response:
            Response<List<OlahPosItem>>
            ) {
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

    private fun tampilFoodUser(olahUsers: List<OlahPosItem>) {
        listGithubUser.layoutManager = LinearLayoutManager(context)
        listGithubUser.adapter = OlahPosAdapter(context!!, olahUsers) {
            val olahUser = it
            tampilToast(context!!, olahUser.id)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}

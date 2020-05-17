package com.example.dietkuyy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietkuyy.data.FoodService
import com.example.dietkuyy.data.FrutiService
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
class FrutiFragment : Fragment() {
override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
}
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {

    return inflater.inflate(R.layout.fragment_fruti, container, false)
}
override fun onViewCreated(
    view: View,
    @Nullable savedInstanceState: Bundle?
) {
    super.onViewCreated(view, savedInstanceState)
    callApiGetFrutiUser()
}
private fun callApiGetFrutiUser() {
    showLoading(context!!, swipeRefreshLayout)
    val httpClient = httpClient()
    val apiRequest = apiRequest<FrutiService>(httpClient)
    val call = apiRequest.getfruit()
    call.enqueue(object : Callback<List<FrutiPosItem>> {
        override fun onFailure(call: Call<List<FrutiPosItem>>, t: Throwable) {
            dismissLoading(swipeRefreshLayout)
        }
        override fun onResponse(call: Call<List<FrutiPosItem>>, response:
        Response<List<FrutiPosItem>>
        ) {
            dismissLoading(swipeRefreshLayout)
            when {
                response.isSuccessful ->
                    when {
                        response.body()?.size != 0 ->
                            tampilFrutiUser(response.body()!!)
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

private fun tampilFrutiUser(frutiUsers: List<FrutiPosItem>) {
    listGithubUser.layoutManager = LinearLayoutManager(context)
    listGithubUser.adapter = FrutiPosAdapter(context!!, frutiUsers) {
        val frutiUser = it
        tampilToast(context!!, frutiUser.id)
    }
}
override fun onDestroy() {
    super.onDestroy()
    this.clearFindViewByIdCache()
}
}

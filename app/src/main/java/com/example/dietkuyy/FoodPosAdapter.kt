package com.example.dietkuyy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.food_pos_item.*

class FoodPosAdapter(private val context: Context, private val items:
List<FoodPosItem>, private val listener: (FoodPosItem)-> Unit) :
    RecyclerView.Adapter<FoodPosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.food_pos_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: FoodPosItem, listener: (FoodPosItem) -> Unit) {
            txtUsername.text = item.id
            txtUserRepo.text = item.nama
            Glide.with(context).load(item.img).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }
    }
}
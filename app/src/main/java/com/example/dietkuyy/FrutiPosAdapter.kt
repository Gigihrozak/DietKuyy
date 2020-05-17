package com.example.dietkuyy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fruti_pos_item.*

class FrutiPosAdapter(private val context: Context, private val items:
List<FrutiPosItem>, private val listener: (FrutiPosItem)-> Unit) :
    RecyclerView.Adapter<FrutiPosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.fruti_pos_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: FrutiPosItem, listener: (FrutiPosItem) -> Unit) {
            txtUsername.text = item.nama
            txtUserRepo.text = item.calori
            Glide.with(context).load(item.img).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }
    }
}
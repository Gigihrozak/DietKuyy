package com.example.dietkuyy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.food_pos_item.*

class OlahPosAdapter(private val context: Context, private val items:
List<OlahPosItem>, private val listener: (OlahPosItem)-> Unit) :
    RecyclerView.Adapter<OlahPosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.list_user_item,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: OlahPosItem, listener: (OlahPosItem) -> Unit) {
            txtUsername.text = item.nama
            txtUserRepo.text = item.kal
            Glide.with(context).load(item.img).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }
    }
}
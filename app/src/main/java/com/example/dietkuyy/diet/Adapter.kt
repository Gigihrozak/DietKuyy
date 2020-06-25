package com.example.dietkuyy.diet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.dietkuyy.R

class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Beratmo> )
    : ArrayAdapter<Beratmo>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textStatus = view.findViewById<TextView>(R.id. textStatus)

        val berat = list[position]

        textNama.text = berat.tanggal
        textStatus.text = berat.berat

        return view

    }
}
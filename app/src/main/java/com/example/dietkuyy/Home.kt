package com.example.dietkuyy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    lateinit var fragview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragview = inflater.inflate(R.layout.fragment_home, container, false)

ideal.setOnClickListener { bobot() }

        return fragview
    }

    private fun bobot() {
        val intent = Intent(context!!, Ideal::class.java)
        startActivity(intent)
    }

}





@file:Suppress("UNREACHABLE_CODE")

package com.example.dietkuyy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import kotlinx.android.synthetic.main.fragment_profil.*

/**
 * A simple [Fragment] subclass.
 */
 class Profil : Fragment() {
    private var kuyy: TextView? = null
     private var baseContext = null
    lateinit var fragview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        fragview =  inflater.inflate(R.layout.fragment_profil, container, false)
        kuyy = fragview.findViewById(R.id.tv_namaMain)
        button_logoutMain.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
        return fragview
    }

    private fun finish() {
        TODO("Not yet implemented")
    }
}


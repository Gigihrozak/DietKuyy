package com.example.dietkuyy.diet

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietkuyy.HomeFragment

import com.example.dietkuyy.R
import com.example.dietkuyy.login.Users
import com.example.dietkuyy.util.tampilToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.core.Context
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_beratbadan.*
import kotlinx.android.synthetic.main.fragment_profil.*

/**
 * A simple [Fragment] subclass.
 */
class beratbadan : Fragment() {
    lateinit var list : MutableList<Beratmo>

    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_beratbadan, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        auth = FirebaseAuth.getInstance()
        proses.setOnClickListener {
            prosesSave()

        }


    }
    private fun prosesSave() {
        val nama = tanggal.text.toString()
        val status = berat.text.toString()
        val jumlah = Beratmo(nama, status)
        val getUserID = auth?.getCurrentUser()?.getUid().toString()


        ref.child(getUserID).child("jumlah").push().setValue(jumlah).addOnCompleteListener {
            Toast.makeText(context, "Successs",Toast.LENGTH_SHORT).show()
            tanggal.setText("")
            berat.setText("")
        }
    }
    private fun getData() {
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()

        list = mutableListOf()
        ref.child(getUserID).child("jumlah").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val berat = h.getValue(Beratmo::class.java)
                        list.add(berat!!)
                    }
                    val adapter = Adapter(context!!,R.layout.beratlist,list)
                    listView.adapter = adapter
                }
            }
        })
    }
    }






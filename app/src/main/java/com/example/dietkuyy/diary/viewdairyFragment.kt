package com.example.dietkuyy.diary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.example.dietkuyy.R
import com.example.dietkuyy.diet.Adapter
import com.example.dietkuyy.diet.Beratmo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_beratbadan.*
import kotlinx.android.synthetic.main.fragment_home.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_viewdairy.*

/**
 * A simple [Fragment] subclass.
 */
class viewdairyFragment : Fragment() {
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var list : MutableList<Diarymo>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vie= inflater.inflate(R.layout.fragment_viewdairy, container, false)
        listView = vie.findViewById(R.id.listView)
        return vie

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        fab.setOnClickListener {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    private fun getData(){
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()

        list = mutableListOf()
        ref.child(getUserID).child("diary").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    list.clear()
                    for (h in p0.children){
                        val berat = h.getValue(Diarymo::class.java)
                        list.add(berat!!)
                    }
                    val adapter = diaryAdapter(context!!,R.layout.item_diary,list)
                    listView.adapter = adapter
                }
            }
        })
        }


    }
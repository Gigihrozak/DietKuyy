package com.example.dietkuyy.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.dietkuyy.HomeFragment

import com.example.dietkuyy.R
import com.example.dietkuyy.login.LoginActivity.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.fragment_beratbadan.*
import kotlinx.android.synthetic.main.fragment_beratbadan.berat
import kotlinx.android.synthetic.main.fragment_beratbadan.proses
import kotlinx.android.synthetic.main.fragment_beratbadan.tanggal
import kotlinx.android.synthetic.main.fragment_diaryku.*

/**
 * A simple [Fragment] subclass.
 */
class diaryku : Fragment() {
    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      val  bu= inflater.inflate(R.layout.fragment_diaryku, container, false)
        val vie = bu.findViewById<Button>(R.id.vie) as Button
        vie.setOnClickListener {
            val fragmen=  viewdairyFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return bu
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        proses.setOnClickListener {
            prosesSave()

        }


    }
    private fun prosesSave() {
        val time = tanggal.text.toString()
        val acti = berat.text.toString()
        val not = isi.text.toString()
        val jumlah = Diarymo(time,acti,not)
        val getUserID = auth?.getCurrentUser()?.getUid().toString()


        ref.child(getUserID).child("diary").push().setValue(jumlah).addOnCompleteListener {
            FirebaseMessaging.getInstance().subscribeToTopic("Sukses")
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.title)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.title_onboarding_1)
                    }
                    Log.d(TAG, msg)
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            tanggal.setText("")
            berat.setText("")
            isi.setText("")
        }
    }
    private fun lanjut(){

    }
}

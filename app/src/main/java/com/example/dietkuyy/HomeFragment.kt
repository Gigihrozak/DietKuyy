package com.example.dietkuyy

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import com.example.dietkuyy.diary.diaryku
import com.example.dietkuyy.diet.beratbadan
import com.example.dietkuyy.login.ProfilFragment
import com.example.dietkuyy.rencana.EditActivity
import com.example.dietkuyy.rencana.rencanaFragment
import com.example.dietkuyy.rencana.room
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_profil.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


    val  v= inflater.inflate(R.layout.fragment_home, container, false)


val btn_home = v.findViewById<Button>(R.id.btn_home) as Button
        val btn_fruti = v.findViewById<Button>(R.id.btn_fruti) as Button
        val btn_food = v.findViewById<Button>(R.id.btn_food) as Button
        val btn_spotr = v.findViewById<Button>(R.id.btn_spotr) as Button
        val btn_ideal = v.findViewById<Button>(R.id.btn_ideal) as Button
        val btn_weight = v.findViewById<Button>(R.id.btn_weig) as Button
        val btn_dairy = v.findViewById<Button>(R.id.btn_dairy) as Button
        val btn_profil = v.findViewById<Button>(R.id.btn_profil) as Button
        val btn_cy = v.findViewById<Button>(R.id.btn_cy) as Button
        btn_home.setOnClickListener {
            val fragmen=  HomeFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_fruti.setOnClickListener {
            val fragmen= FrutiFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_food.setOnClickListener {
            val fragmen= Food()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_spotr.setOnClickListener {
            val fragmen= List_Day()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_ideal.setOnClickListener {
            val fragmen= Ideal()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_weight.setOnClickListener {
            val fragmen= beratbadan()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_dairy.setOnClickListener {
            val fragmen= diaryku()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_profil.setOnClickListener {
            val fragmen= ProfilFragment()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.pager_framelayout,fragmen)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        btn_cy.setOnClickListener {
            startActivity(Intent(context, room::class.java))
        }
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}





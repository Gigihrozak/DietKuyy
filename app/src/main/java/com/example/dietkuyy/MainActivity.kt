package com.example.dietkuyy

import android.content.Intent
import android.content.res.Configuration
import android.net.sip.SipManager.newInstance
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.example.dietkuyy.R.drawable
import com.example.dietkuyy.R.layout
import com.example.dietkuyy.diary.diaryku
import com.example.dietkuyy.diet.beratbadan
import com.example.dietkuyy.login.LoginActivity
import com.example.dietkuyy.login.ProfilFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var toolbar: Toolbar
    var tabLayout: TabLayout? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    var frameLayout: FrameLayout? = null
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    var fragment: Fragment? = null
    val TAG = "MyMessage"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        frameLayout = findViewById<FrameLayout>(R.id.pager_framelayout)

        fragment = HomeFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.pager_framelayout, fragment as HomeFragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction!!.commit()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when (tab.position) {
                    0 -> fragment = HomeFragment()
                    1 -> fragment = diaryku()
                    2 -> fragment = ProfilFragment()

                }
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(R.id.pager_framelayout, fragment!!)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater :MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId ==R.id.home) {
            val fragmen= HomeFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()

        }else if(item.itemId ==R.id.fruit) {
            val fragmen= FrutiFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.sport) {
            val fragmen= List_Day()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.viewPager,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.note) {
            val fragmen= diaryku()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.viewPager,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.deal) {
            val fragmen= Ideal()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.grafik) {
            val fragmen= beratbadan()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.deal) {
            val fragmen= Ideal()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.food) {
            val fragmen= Food()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }else if(item.itemId ==R.id.profil) {
            val fragmen= ProfilFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.pager_framelayout,fragmen)
            transaction.commit()
        }
        return true
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

    return true
    }

}




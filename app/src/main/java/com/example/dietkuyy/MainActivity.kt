package com.example.dietkuyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.dietkuyy.R.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.ui.NavigationUI.setupWithNavController as setupWithNavController1

class MainActivity : AppCompatActivity() {
    private val manuIcon = arrayOf(drawable.ic_group, drawable.ic_tomato, drawable.ic_calendar)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val adapter = TabAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_group)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_tomato)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_food)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_tibon)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.ic_healthy)

    }
    class TabAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
        private val tabName : Array<String> = arrayOf("Home", "Fruit", "Food","Ideal","Sport")
        override fun getItem(position: Int): Fragment = when (position) {
            0 -> HomeFragment()
            1 -> FrutiFragment()
            2 -> Food()
            3 ->Ideal()
            4->List_Day()

            else -> HomeFragment()
        }

        override fun getCount(): Int = 5
        override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater :MenuInflater = menuInflater
         inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        if(item.itemId ==R.id.kuyy) {
            startActivity(Intent(baseContext,logout::class.java));
        }
        return true
    }

    }


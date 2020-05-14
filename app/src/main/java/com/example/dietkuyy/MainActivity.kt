package com.example.dietkuyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dietkuyy.R.*
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

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
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_calendar)

    }
    class TabAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
        private val tabName : Array<String> = arrayOf("Home", "Food", "Jadwal")
        override fun getItem(position: Int): Fragment = when (position) {
            0 -> Home()
            1 -> Food()
            2 -> Jadwal()
            else -> Home()
        }

        override fun getCount(): Int = 3
        override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

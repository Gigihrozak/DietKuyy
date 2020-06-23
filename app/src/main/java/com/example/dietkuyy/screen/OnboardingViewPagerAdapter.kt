package com.example.dietkuyy.screen

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.dietkuyy.R

class OnboardingViewPagerAdapter(manager: FragmentManager,
                                 private val context : Context
) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_1),
                context.resources.getString(R.string.description_onboarding_1),
                R.raw.lottie_developer
            )
            1 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_2),
                context.resources.getString(R.string.description_onboarding_2),
                R.raw.sepeda
            )
            2 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_3),
                context.resources.getString(R.string.description_onboarding_3),
                R.raw.dokter
            )
            3 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_4),
                context.resources.getString(R.string.description_onboarding_4),
                R.raw.grafik
            )
            else -> null
        }!!
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private const val NUM_ITEMS = 4
    }

}
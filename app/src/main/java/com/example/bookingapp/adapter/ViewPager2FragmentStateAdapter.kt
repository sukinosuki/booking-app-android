package com.example.bookingapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2FragmentStateAdapter(
    fa: FragmentActivity,
    private var fragmentList: List<Fragment>
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}
package com.example.news_app_android.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(var fragment: FragmentActivity, var arr: ArrayList<Fragment>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return arr.size
    }


    override fun createFragment(position: Int): Fragment {
        return arr[position]
    }


}
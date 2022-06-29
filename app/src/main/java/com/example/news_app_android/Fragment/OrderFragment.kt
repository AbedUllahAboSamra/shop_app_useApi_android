package com.example.news_app_android.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.news_app_android.R
import com.example.news_app_android.adapters.mmPagerAdapterMainLayout
import com.example.news_app_android.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentOrderBinding.inflate(layoutInflater)












        return binding.root
    }
}
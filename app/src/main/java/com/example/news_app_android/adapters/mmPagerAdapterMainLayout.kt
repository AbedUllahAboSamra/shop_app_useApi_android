package com.example.news_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.news_app_android.databinding.ProductArryDesignBinding

class mmPagerAdapterMainLayout(var context: Context, var images: ArrayList<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var binding = ProductArryDesignBinding.inflate(LayoutInflater.from(context), null, false)
        binding.imageView2.setImageResource(images[position])
        container.addView(binding.root)
        return binding.root


    }
}
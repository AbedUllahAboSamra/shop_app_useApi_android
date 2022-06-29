package com.example.news_app_android.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.news_app_android.R
import com.example.news_app_android.Screens.SplachActivity
import com.example.news_app_android.adapters.ProductAdapterHomeF
import com.example.news_app_android.adapters.mmPagerAdapterMainLayout
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.FragmentMainBinding
import com.example.news_app_android.db.MyDataBase
import com.google.android.material.tabs.TabLayout


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         //get All Product From Data Base



        var binding = FragmentMainBinding.inflate(layoutInflater)



        // send code  product adapter
        binding.productRecycleView.layoutManager = GridLayoutManager(requireContext(), 2)
        //end send code

        /////////////////////
        //start  pager code

        var imgs = ArrayList<Int>()
        imgs.add(R.drawable.sh1)
        imgs.add(R.drawable.sh2)
        imgs.add(R.drawable.sh3)
        imgs.add(R.drawable.sh4)
        imgs.add(R.drawable.sh5)


        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.currentItem = Math.round((imgs.size / 2).toDouble()).toInt()
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.pageMargin = 30
        // set up tab layout with view pager
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.setPageTransformer(false, object : ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                var r = 1 - Math.abs(position)
                page.scaleY = .85f + r * .12f

                page.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            }
        })

        var myPagerAdapter = mmPagerAdapterMainLayout(requireContext(), imgs)
        binding.viewPager.adapter = myPagerAdapter

        //end  pager code

        /////////////////////


        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onTabSelected(tab: TabLayout.Tab?) {


                //filter the categorys
                var arr = SplachActivity.productArrayList.filter { it ->
                    it.category == SplachActivity.categoryArray[tab!!.id]
                } as ArrayList<productModle>
                // end filter the categorys

                var productAdapter = ProductAdapterHomeF(arr, requireContext())
                binding.productRecycleView.adapter = productAdapter
                productAdapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        /////////////////////
        // add the tabs of the categorys on tab layout
        for (i in 0 until SplachActivity.categoryArray.size) {


            binding.tabLayout2.addTab(
                binding.tabLayout2.newTab().setText(SplachActivity.categoryArray[i]).setId(i)
            )
        }
        //end add the tabs of the categorys on tab layout

   return binding.root
    }

}
package com.example.news_app_android

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.news_app_android.Fragment.PaymentFragment
import com.example.news_app_android.Screens.LoginActivity
import com.example.news_app_android.Screens.SplachActivity
import com.example.news_app_android.adapters.ProductAdapterHomeF
import com.example.news_app_android.adapters.mmPagerAdapter
import com.example.news_app_android.adapters.mmPagerAdapterMainLayout
import com.example.news_app_android.classs.UserModle
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.ActivityMainBinding
import com.example.news_app_android.databinding.HeaderDrawerBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mytoolbar)
        supportActionBar!!.title = ""

        //start code drawer layout


        val togle =
            object : ActionBarDrawerToggle(this, binding.myDrawerlayout, binding.mytoolbar, 0, 0) {

                val scaleFactor = 6f
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    var slideX = drawerView.width * slideOffset
                    binding.content.setTranslationX(slideX)

                    binding.content.setScaleX(1 - (slideOffset / scaleFactor))
                    binding.content.setScaleY(.99f - (slideOffset / scaleFactor))
                }


            }

        binding.myDrawerlayout.addDrawerListener(togle)
        binding.myDrawerlayout.setScrimColor(Color.TRANSPARENT)
        binding.myDrawerlayout.setDrawerElevation(0f)
        togle.syncState()


        //header click listener
        var headerView = binding.navView.getHeaderView(0)
        var headerBinding = HeaderDrawerBinding.bind(headerView)

        headerBinding.logout.setOnClickListener {
          //  var sharedPef = getSharedPreferences("MY", MODE_PRIVATE)
           // var edit = sharedPef.edit()
           // edit.clear()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()


        }


//        Picasso.get().load(UserModle.userModle.imageUrl).into(headerBinding.profileImage)
        headerBinding.name.text = UserModle.userModle.userName

        headerBinding.close.setOnClickListener {
            binding.myDrawerlayout.closeDrawer(Gravity.LEFT, true)
        }


        //end code drawer layout


        //******************//


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shopIcon -> {
                supportFragmentManager.beginTransaction().addToBackStack("payment").replace(
                    R.id.Container, PaymentFragment()
                ).commit()

            }
            R.id.logout -> {
                //  var sharedPef = getSharedPreferences("MY", MODE_PRIVATE)
                //  var edit = sharedPef.edit()
                //  edit.clear()
                //  edit.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

            }
        }


        return super.onOptionsItemSelected(item)
    }


}
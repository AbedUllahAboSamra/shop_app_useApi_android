package com.example.news_app_android.Screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.news_app_android.R
import com.example.news_app_android.adapters.SugistionAdapter
import com.example.news_app_android.adapters.mmPagerAdapter
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.ActivityProductBinding
import com.example.news_app_android.db.MyDataBase
import com.google.android.material.snackbar.Snackbar
import kotlin.collections.ArrayList


class ProductActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var allProduct = MyDataBase(this).getAllProductInBag()


        var objectProduct = intent.getSerializableExtra("prod") as productModle

        binding.viewPager.currentItem = objectProduct.images.size / 2.toInt()

        var db = MyDataBase(this)
        var adapter = mmPagerAdapter(this, objectProduct.images)
        binding.viewPager.adapter = adapter
        binding.categorytyper.text = objectProduct.category
        binding.viewPager.setOffscreenPageLimit(3)
        binding.viewPager.setPageMargin(50)
        binding.viewPager.clipChildren = false
        binding.viewPager.clipToPadding = false


        binding.viewPager.setPageTransformer(false,
            ViewPager.PageTransformer { page, position ->
                var r = 1 - Math.abs(position)
                page.scaleY = .85f + r * .12f

                page.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            })



        binding.imageView3.setOnClickListener {
            this.onBackPressed()
        }

        binding.productname.text = objectProduct.title
        binding.productdescribtion.text = objectProduct.description
        binding.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.oldPrice.text = "${objectProduct.price}$"

        binding.newPrice.text =
            "${Math.round((objectProduct.price - (objectProduct.price * (objectProduct.discountPercentage / 100))))}$"
        binding.ratingBar.rating = objectProduct.rating.toFloat()


        var suggestionArr = SplachActivity.productArrayList.filter { it ->
            it.category == objectProduct.category
        } as ArrayList<productModle>
        binding.suggistionRecycle.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        var suggAdapter = SugistionAdapter(this, suggestionArr)
        binding.suggistionRecycle.adapter = suggAdapter


        binding.floatingActionButton.setOnClickListener {
            var snack:Snackbar


            if(!allProduct.contains(objectProduct)){
            var boo = db.addProductToBag(objectProduct.id)
             if (boo) {

                 snack =
                     Snackbar.make(it, "The product has been added to the bag", Snackbar.LENGTH_LONG)
                snack.setBackgroundTint(Color.rgb(28,181,74))
                snack.show()
            }

        }else{
                snack =
                    Snackbar.make(it, "The product already exist", Snackbar.LENGTH_LONG)
                snack.setBackgroundTint(Color.rgb(64,64,64))
                snack.show()

            }


        }


    }


}
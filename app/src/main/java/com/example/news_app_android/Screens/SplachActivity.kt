package com.example.news_app_android.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.news_app_android.MainActivity
import com.example.news_app_android.R
import com.example.news_app_android.classs.UserModle
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.classs.myApp
import com.example.news_app_android.db.MyDataBase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SplachActivity : AppCompatActivity() {

    companion object {
        var categoryArray = ArrayList<String>()
        var productArrayList = ArrayList<productModle>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        var sharedPef = getSharedPreferences("MY", MODE_PRIVATE)
        var myUID = sharedPef.getString("uid","")
//        var db = Firebase.firestore
 Log.e("ASD","$myUID")
        //category req
        var arrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "${myApp.htttp}products/categories",
            null,
            Response.Listener { response ->

                for (i in 0 until response.length()) {
                    categoryArray.add(response.getString(i))
                }

            },
            Response.ErrorListener { error -> },

            )
        myApp.getInstance()!!.addtoRequstQueue(arrayRequest, "asd")
        //category req


        // product Request

        var productRequest = JsonObjectRequest(
            Request.Method.GET,
            "${myApp.htttp}products",
            null,
            Response.Listener { response ->
                var arr = response.getJSONArray("products")



                for (i in 0 until arr.length()) {
                    var ob = arr.getJSONObject(i)

                    var images = ob.getJSONArray("images")

                    var arrimages = ArrayList<String>()

                    for (p in 0 until images.length()) {
                        arrimages.add(images.getString(p))
                    }

                    productArrayList.add(
                        productModle(
                            id = ob.getInt("id"),
                            title = ob.getString("title"),
                            description = ob.getString("description"),
                            price = ob.getDouble("price"),
                            discountPercentage = ob.getDouble("discountPercentage"),
                            rating = ob.getDouble("rating"),
                            stock = ob.getInt("stock"),
                            brand = ob.getString("brand"),
                            category = ob.getString("category"),
                            mainImageUrl = ob.getString("thumbnail"),
                            images = arrimages,
                        )
                    )


                }
                Handler().postDelayed(
                    {
                        if (myUID!!.isNotEmpty()) {
//                            db.collection("users")
//                                .document(myUID)
//                                .get().addOnSuccessListener { it ->
//                                    UserModle.userModle.userName = it.get("userName").toString()
//                                    UserModle.userModle.uid = it.get("uid").toString()
//                                    UserModle.userModle.password = it.get("password").toString()
//                                    UserModle.userModle.email = it.get("email").toString()
//                                    UserModle.userModle.imageUrl = it.get("imageUrl").toString()
//
//                                    startActivity(
//                                        Intent(
//                                            this,
//                                            MainActivity::class.java
//                                        )
//                                    )
//                                    finish()
//
//
//                                }

                        } else {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }

                    }, 3000
                )

            },
            Response.ErrorListener { error ->

            })

        myApp.getInstance()!!.addtoRequstQueue(productRequest, "asd")




        MyDataBase(this).getAllProductInBag()


    }
}
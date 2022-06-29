package com.example.news_app_android.classs.modle

import java.io.Serializable

data class productModle(
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var discountPercentage: Double,
    var rating: Double,
    var stock: Int,
    var brand: String,
    var category: String,
    var mainImageUrl: String,
    var images: ArrayList<String>

):Serializable{

}
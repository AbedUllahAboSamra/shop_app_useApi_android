package com.example.news_app_android.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_android.Screens.ProductActivity
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.ProductDesignBinding
import com.squareup.picasso.Picasso

class ProductAdapterHomeF(var arrayList: ArrayList<productModle>, var context: Context) :
    RecyclerView.Adapter<ProductAdapterHomeF.myViewHolder>() {

    class myViewHolder(var binding: ProductDesignBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        var binding =
            ProductDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Picasso.get().load(arrayList[position].mainImageUrl).into(holder.binding.imageView)


        holder.binding.priceRight.text =
            "${Math.round((arrayList[position].price - (arrayList[position].price * (arrayList[position].discountPercentage / 100))))}$"
        holder.binding.teProductText.text = arrayList[position].title

        holder.binding.root.setOnClickListener {
            var i = Intent(context, ProductActivity::class.java)
            i.putExtra("prod",arrayList[position])
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(i)


        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
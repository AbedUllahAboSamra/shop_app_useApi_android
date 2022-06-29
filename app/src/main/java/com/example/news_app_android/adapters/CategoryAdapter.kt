package com.example.news_app_android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_android.databinding.CaregoreDesignBinding

class CategoryAdapter(var context: Context, var array: ArrayList<String>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHollder>() {

    class MyViewHollder(var binding: CaregoreDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHollder {
        var binding = CaregoreDesignBinding.inflate(LayoutInflater.from(context), parent, false)

        return MyViewHollder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHollder, position: Int) {
        holder.binding.TVCategory.text = array[position]


    }

    override fun getItemCount(): Int {
        return array.size
    }
}
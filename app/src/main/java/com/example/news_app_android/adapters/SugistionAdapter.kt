package com.example.news_app_android.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_android.Screens.ProductActivity
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.SuggistionDesginBinding
import com.squareup.picasso.Picasso

class SugistionAdapter(var context: Context, var array: ArrayList<productModle>) :
    RecyclerView.Adapter<SugistionAdapter.myViewHolder>() {

    class myViewHolder(var bindiing: SuggistionDesginBinding) :
        RecyclerView.ViewHolder(bindiing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var binding = SuggistionDesginBinding.inflate(LayoutInflater.from(context), parent, false)

        return myViewHolder(binding)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        Picasso.get().load(array[position].mainImageUrl).into(holder.bindiing.imageView)
        holder.bindiing.title.text = array[position].title
        holder.bindiing.root.setOnClickListener {
            val i = Intent(context, ProductActivity::class.java)
            i.putExtra("prod", array[position])
            context.startActivity(i)

        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}
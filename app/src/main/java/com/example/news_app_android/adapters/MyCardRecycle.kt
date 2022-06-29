package com.example.news_app_android.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_android.Screens.ProductActivity
import com.example.news_app_android.classs.modle.productModle
import com.example.news_app_android.databinding.BagShopItemDesignBinding
import com.example.news_app_android.db.MyDataBase
import com.squareup.picasso.Picasso


class MyCardRecycle(var context: Context, var array: ArrayList<productModle>) :
    RecyclerView.Adapter<MyCardRecycle.myViewHolder>() {


    var ii = 0.0
    var listen = MutableLiveData<Double>()

    class myViewHolder(var binding: BagShopItemDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var binding = BagShopItemDesignBinding.inflate(LayoutInflater.from(context), parent, false)


        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        var price =
            Math.round((array[position].price - (array[position].price * (array[position].discountPercentage / 100))))
        var quantety = 1


        ii +=price
        listen.value = ii


        holder.binding.price.text = "${price * quantety} $"
        holder.binding.alkmeaTExtView.text = "$quantety"


        Picasso.get().load(array[position].mainImageUrl).into(holder.binding.productImage)
        holder.binding.productname.text = array[position].title
        holder.binding.productImage.setOnClickListener {
            var i = Intent(context, ProductActivity::class.java)
            i.putExtra("prod", array[position])
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }


        holder.binding.btnplus.setOnClickListener {
            quantety += 1

            ii +=price
            listen.value = ii
            holder.binding.price.text = "${price * quantety} $"
            holder.binding.alkmeaTExtView.text = "$quantety"

        }
        holder.binding.btnmins.setOnClickListener {
            if (quantety > 1) {
                quantety--
                ii -=price
                listen.value = ii
                  holder.binding.price.text = "${price * quantety} $"
                holder.binding.alkmeaTExtView.text = "$quantety"

            }
        }
        holder.binding.root.setOnClickListener {
        }


    }

    override fun getItemCount(): Int {
        return array.size
    }

    fun deleteItem(i: Int, id: Int) {
        array.removeAt(i)
        notifyDataSetChanged()
        try {
            var s = MyDataBase(context).deleteProductFromBag(id)
        } catch (e: Exception) {
        }
    }


}
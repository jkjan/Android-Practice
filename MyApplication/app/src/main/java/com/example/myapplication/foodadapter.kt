package com.example.myapplication

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class Food(val image: String, val name : String)

class foodrvadapter (val context: Context, val foodlist : ArrayList<Food>) :
    RecyclerView.Adapter<foodrvadapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_food, parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(foodlist[position], context)
    }



    inner class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodimage = itemView.findViewById<ImageView>(R.id.ivfood)
        val foodname = itemView.findViewById<TextView>(R.id.name)

        fun bind(sood : Food, context : Context)  {
            if (sood.image != "") {
                val resource = context.resources.getIdentifier(sood.image, "drawable", context.packageName)

                foodimage.setImageResource(resource)
            }

            else {
                foodimage.setImageResource(R.mipmap.ic_launcher)
            }

            foodname.text = sood.name
        }
    }
}
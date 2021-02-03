package com.example.android.testingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.testingapp.R
import com.example.android.testingapp.data.response_entity.CompanyResponseItem
import com.example.android.testingapp.other.Constants.BASE_URL

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var data = listOf<CompanyResponseItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.name
        Glide.with(holder.itemView).load(BASE_URL+"/"+ item.img).into(holder.imageView)
    }


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.imageView_item)
        val title = itemView.findViewById<TextView>(R.id.textView_title)
    }

}
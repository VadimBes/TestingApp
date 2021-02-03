package com.example.android.testingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.testingapp.data.response_entity.CompanyResponseItem
import com.example.android.testingapp.databinding.RecyclerItemBinding

class RecyclerViewAdapter(val clickListener: RecyclerClickListener): ListAdapter<CompanyResponseItem, RecyclerViewAdapter.ViewHolder>(
    RecyclerDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    class ViewHolder private constructor(val binding:RecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: CompanyResponseItem,
            clickListener: RecyclerClickListener
        ) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.textViewTitle.text = item.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class RecyclerDiffCallback:DiffUtil.ItemCallback<CompanyResponseItem>() {
    override fun areItemsTheSame(
        oldItem: CompanyResponseItem,
        newItem: CompanyResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CompanyResponseItem,
        newItem: CompanyResponseItem
    ): Boolean {
        return oldItem==newItem
    }
}

class RecyclerClickListener(val clickListener:(itemId:String)->Unit){
    fun onClick(item:CompanyResponseItem) = clickListener(item.id)
}
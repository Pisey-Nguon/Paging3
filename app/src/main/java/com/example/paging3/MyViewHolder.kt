package com.example.paging3

import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.databinding.MyItemLayoutBinding

class MyViewHolder(private val binding:MyItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    fun onBind(item:UserData){
        binding.tvTitle.text = item.email
    }

}
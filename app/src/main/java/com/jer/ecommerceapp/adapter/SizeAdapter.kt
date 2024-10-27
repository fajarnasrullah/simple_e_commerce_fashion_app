package com.jer.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.databinding.ItemColorBinding
import com.jer.ecommerceapp.databinding.ItemSizeBinding

class SizeAdapter(private val item: List<String>):
    RecyclerView.Adapter<SizeAdapter.ColorViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    class ColorViewHolder(val binding: ItemSizeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        context = parent.context
        val binding = ItemSizeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ColorViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.sizeLayout.setBackgroundResource(R.color.purple)
            holder.binding.tvSize.setTextColor(context.resources.getColor(R.color.white))

        } else {

            holder.binding.sizeLayout.setBackgroundResource(R.drawable.bg_grey)
            holder.binding.tvSize.setTextColor(context.resources.getColor(R.color.black))

        }





    }

}
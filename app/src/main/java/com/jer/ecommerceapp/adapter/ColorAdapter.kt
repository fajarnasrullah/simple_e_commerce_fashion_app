package com.jer.ecommerceapp.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.databinding.ItemBrandBinding
import com.jer.ecommerceapp.databinding.ItemColorBinding
import com.jer.ecommerceapp.model.BrandModel

class ColorAdapter( private val item: List<String>):
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    class ColorViewHolder(val binding: ItemColorBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        context = parent.context
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ColorViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(item[position])
            .into(holder.binding.ivPic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.bg_grey_selected)

        } else {

            holder.binding.colorLayout.setBackgroundResource(R.drawable.bg_grey)

        }





    }

}
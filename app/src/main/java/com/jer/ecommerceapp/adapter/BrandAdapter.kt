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
import com.jer.ecommerceapp.model.BrandModel

class BrandAdapter( private val brandListModel: List<BrandModel>):
    RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

        private lateinit var context: Context
        private var selectedPosition = -1
        private var lastSelectedPosition = -1


    class BrandViewHolder(val binding: ItemBrandBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        context = parent.context
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount(): Int = brandListModel.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val item = brandListModel[position]

        holder.binding.tvBrand.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.imageView)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.binding.tvBrand.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position) {
            holder.binding.imageView.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.bg_btn)
            ImageViewCompat.setImageTintList(holder.binding.imageView, ColorStateList.valueOf(context.getColor(R.color.white)))

            holder.binding.tvBrand.visibility = View.VISIBLE
        } else {
            holder.binding.imageView.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.bg_grey)
            ImageViewCompat.setImageTintList(holder.binding.imageView, ColorStateList.valueOf(context.getColor(R.color.black)))

            holder.binding.tvBrand.visibility = View.GONE
        }





    }

}
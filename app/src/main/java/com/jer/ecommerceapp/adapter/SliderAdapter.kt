package com.jer.ecommerceapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.jer.ecommerceapp.databinding.ItemImageSlideBinding
import com.jer.ecommerceapp.model.SliderModel

class SliderAdapter(
    private var sliderList: List<SliderModel>,
    private val viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context
    private val runnable = Runnable {
        sliderList = sliderList
        notifyDataSetChanged()
    }

    class SliderViewHolder(binding: ItemImageSlideBinding): RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageSlide

        fun setImage(sliderItem: SliderModel, context: Context) {
            val requestOption = RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItem.url)
                .apply(requestOption)
                .into(imageView)
            Log.d("ImageURL", sliderItem.url)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        val binding = ItemImageSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int = sliderList.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderList[position], context)
        if (position == sliderList.size - 1) {
            viewPager2.post(runnable)
        }
    }
}
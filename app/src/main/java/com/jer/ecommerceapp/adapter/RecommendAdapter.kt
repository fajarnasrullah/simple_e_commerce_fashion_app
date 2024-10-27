package com.jer.ecommerceapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.jer.ecommerceapp.databinding.ItemRecommendBinding
import com.jer.ecommerceapp.model.RecommendModel
import com.jer.ecommerceapp.ui.DetailActivity

class RecommendAdapter(private val recommendListItem: List<RecommendModel>): RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {

    private lateinit var context: Context
    class RecommendViewHolder(val binding: ItemRecommendBinding): ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        context = parent.context
        val binding = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun getItemCount(): Int = recommendListItem.size

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
//        val item = recommendListItem[position]

        holder.binding.tvTitle.text = recommendListItem[position].title
        holder.binding.tvPrice.text = "$" + recommendListItem[position].price.toString()
        holder.binding.tvRating.text = recommendListItem[position].rating.toString()

        val requestOption = RequestOptions().transform(CircleCrop())
        Glide.with(holder.itemView.context)
            .load(recommendListItem[position].picUrl[0])
//            .apply(requestOption)
            .into(holder.binding.ivProduct)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", recommendListItem[position])
            context.startActivity(intent)

        }

    }
}
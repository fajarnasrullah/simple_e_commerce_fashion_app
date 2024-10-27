package com.jer.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart
import com.jer.ecommerceapp.databinding.ItemCartBinding
import com.jer.ecommerceapp.model.RecommendModel

class CartAdapter(
    private val cartList: ArrayList<RecommendModel>,
    context: Context,
    val changeNumberItemsListener: ChangeNumberItemsListener
    ): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartManagement = ManagmentCart(context)

    class CartViewHolder(val binding: ItemCartBinding): ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val itemCart = cartList[position]

        holder.binding.tvTitle.text = itemCart.title
        holder.binding.tvPrice.text = "$" + itemCart.price.toString()
        holder.binding.tvCount.text = itemCart.numberInCart.toString()
        holder.binding.tvTotalPrice.text = "$" + Math.round(itemCart.price * itemCart.numberInCart).toString()

        Glide.with(holder.itemView.context)
            .load(itemCart.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.ivProduct)

        holder.binding.btnPlus.setOnClickListener {
            cartManagement.plusItem(cartList, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()

                }

            })
        }

        holder.binding.btnMin.setOnClickListener {
            cartManagement.minusItem(cartList, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int = cartList.size


}
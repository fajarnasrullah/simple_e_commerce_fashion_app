package com.jer.ecommerceapp.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.databinding.ItemMessageBinding
import com.jer.ecommerceapp.model.Message

class FirebaseMessageAdapter(
    options: FirebaseRecyclerOptions<Message>,
    private val currentUsername: String?
) : FirebaseRecyclerAdapter<Message, FirebaseMessageAdapter.MessageViewHolder>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int, model: Message) {
        holder.bind(model)
    }


    inner class MessageViewHolder(private val binding: ItemMessageBinding): ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.tvMessenger.text = item.name
            setTextColor(item.name, binding.tvMessage)
            binding.tvMessage.text = item.text

            Glide.with(itemView.context)
                .load(item.photoUrl)
                .circleCrop()
                .into(binding.ivMessenger)
            if (item.timestamp != null) {
                binding.tvTimestamp.text = DateUtils.getRelativeTimeSpanString(item.timestamp)
            }
        }

        fun setTextColor(userName: String?, textView: TextView) {
            if (currentUsername == userName && userName != null) {
                textView.setBackgroundResource(R.drawable.rounded_message_brown)
            } else {
                textView.setBackgroundResource(R.drawable.rounded_message_blue)
            }
        }
    }

}
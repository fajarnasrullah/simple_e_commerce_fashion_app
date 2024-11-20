package com.jer.ecommerceapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.adapter.RecommendAdapter
import com.jer.ecommerceapp.databinding.ActivityProductsBinding
import com.jer.ecommerceapp.viewModel.MainViewModel

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.recommend.observe(this) {item ->
            binding.rvProducts.adapter = RecommendAdapter(item)
            binding.rvProducts.layoutManager = GridLayoutManager(this, 2)

        }

        viewModel.getRecommend()

        viewModel.isLoading.observe(this) {
            isLoadingBanner(it)
        }
    }


    fun isLoadingBanner(state: Boolean) {
        if (state) {
            binding.progressBanner.visibility = View.VISIBLE
        } else {
            binding.progressBanner.visibility = View.GONE
        }
    }


}
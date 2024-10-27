package com.jer.ecommerceapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.jer.ecommerceapp.adapter.BrandAdapter
import com.jer.ecommerceapp.adapter.RecommendAdapter
import com.jer.ecommerceapp.adapter.SliderAdapter
import com.jer.ecommerceapp.databinding.ActivityMainBinding
import com.jer.ecommerceapp.model.BrandModel
import com.jer.ecommerceapp.model.RecommendModel
import com.jer.ecommerceapp.model.SliderModel
import com.jer.ecommerceapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.banner.observe(this) { item ->
            banners(item)
        }
        viewModel.getBanners()

        viewModel.brand.observe(this) { item ->
            brands(item)
        }
        viewModel.getBrand()


        viewModel.recommend.observe(this) { item ->
            recommends(item)
        }
        viewModel.getRecommend()

        viewModel.isLoading.observe(this) {
            isLoadingBanner(it)
        }
        viewModel.isLoading.observe(this) {
            isLoadingBrand(it)
        }
        viewModel.isLoading.observe(this) {
            isLoadingRecommend(it)
        }

        binding.btnNavCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

    }

    fun banners(images: List<SliderModel>) {
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1 ) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }
    }

    fun brands(brands: List<BrandModel>) {
        binding.rvBrand.adapter = BrandAdapter(brands)
        binding.rvBrand.setHasFixedSize(true)
        binding.rvBrand.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun recommends(recommends: List<RecommendModel>) {
        binding.rvRecom.adapter = RecommendAdapter(recommends)
        binding.rvRecom.layoutManager = GridLayoutManager(this, 2)
    }


    fun isLoadingBanner(state: Boolean) {
        if (state) {
            binding.progressBanner.visibility = View.VISIBLE
        } else {
            binding.progressBanner.visibility = View.GONE
        }
    }

    fun isLoadingBrand(state: Boolean) {
        if (state) {
            binding.progressBrand.visibility = View.VISIBLE
        } else {
            binding.progressBrand.visibility = View.GONE
        }
    }

    fun isLoadingRecommend(state: Boolean) {
        if (state) {
            binding.progressRecom.visibility = View.VISIBLE
        } else {
            binding.progressRecom.visibility = View.GONE
        }
    }
}
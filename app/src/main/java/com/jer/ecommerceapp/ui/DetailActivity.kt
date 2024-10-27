package com.jer.ecommerceapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.adapter.ColorAdapter
import com.jer.ecommerceapp.adapter.SizeAdapter
import com.jer.ecommerceapp.adapter.SliderAdapter
import com.jer.ecommerceapp.databinding.ActivityDetailBinding
import com.jer.ecommerceapp.model.RecommendModel
import com.jer.ecommerceapp.model.SliderModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: RecommendModel
    private var numberInCart = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        managmentCart = ManagmentCart(this)

        getBundle()
        getBanners()
        initList()

        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

    }


    private fun initList() {
        val sizeList = ArrayList<String>()
        for(size in item.size) {
            sizeList.add(size)
        }

        binding.rvSize.adapter = SizeAdapter(sizeList)
        binding.rvSize.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for(color in item.picUrl) {
            colorList.add(color)
        }

        binding.rvColor.adapter = ColorAdapter(colorList)
        binding.rvColor.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun getBanners() {
        val sliderItems = ArrayList<SliderModel>()
        for (image in item.picUrl) {
            sliderItems.add(SliderModel(image))
        }

        binding.viewpagerSlider.adapter = SliderAdapter(sliderItems, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        if (sliderItems.size > 1 ) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }

    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.tvTitle.text = item.title
        binding.tvPrice.text = "Rp. ${item.price}"
        binding.tvDesc.text = item.description
        binding.tvRating.text = item.rating.toString() + "Rating"
        binding.btnBuy.setOnClickListener {
            item.numberInCart = numberInCart
            managmentCart.insertFood(item)

        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnCart.setOnClickListener {
            item.numberInCart = numberInCart
            managmentCart.insertFood(item)
        }
    }
}
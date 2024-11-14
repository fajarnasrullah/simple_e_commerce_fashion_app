package com.jer.ecommerceapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.project1762.Helper.ManagmentCart
import com.jer.ecommerceapp.R
import com.jer.ecommerceapp.adapter.CartAdapter
import com.jer.ecommerceapp.databinding.ActivityCartBinding
import com.jer.ecommerceapp.model.RecommendModel

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        initCartList()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnCheckOut.setOnClickListener {
//            Toast.makeText(this, "The Checkout Feature Is Not Available", Toast.LENGTH_SHORT).show()
            val totalPrice = binding.tvTotal.text.toString().toDoubleOrNull() ?: 0.0
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)

        }

    }


    private fun initCartList() {

        val adapter = CartAdapter(managementCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun onChanged() {
                initCalculation()
            }
        })

        binding.rvItemCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItemCart.adapter = adapter

        if (managementCart.getListCart().isEmpty()) {
            binding.rvItemCart.visibility = View.GONE
            binding.tvEmptyCart.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE

        } else {
            binding.rvItemCart.visibility = View.VISIBLE
            binding.tvEmptyCart.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE

        }


    }

    private fun initCalculation() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managementCart.getTotalFee()*percentTax) * 100) / 100.0
        val total = Math.round((managementCart.getTotalFee() + delivery + tax) * 100) / 100.0
        val itemTotal = Math.round((managementCart.getTotalFee()) * 100) / 100.0

        with(binding) {
            tvTax.text = "$" + tax.toString()
            tvDelivery.text = "$" + delivery.toString()
            tvTotal.text = "$" + total.toString()
            tvSubtotal.text = "$" + itemTotal.toString()
        }
    }
}
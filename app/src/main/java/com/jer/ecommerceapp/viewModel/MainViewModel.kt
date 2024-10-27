package com.jer.ecommerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jer.ecommerceapp.model.BrandModel
import com.jer.ecommerceapp.model.RecommendModel
import com.jer.ecommerceapp.model.SliderModel

class MainViewModel {

    private val _banner = MutableLiveData<List<SliderModel>>()
    val banner : LiveData<List<SliderModel>> = _banner

    private val _brand = MutableLiveData<List<BrandModel>>()
    val brand: LiveData<List<BrandModel>> = _brand

    private val _recommend = MutableLiveData<List<RecommendModel>>()
    val recommend: LiveData<List<RecommendModel>> = _recommend

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun getBanners() {
        val bannersRef = firebaseDatabase.getReference("Banner")
        _isLoading.value = true
        bannersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _isLoading.value = false
                val listBanners = mutableListOf<SliderModel>()
                for (snapshotChild in snapshot.children) {
                    val item = snapshotChild.getValue(SliderModel::class.java)
                    if (item != null) {
                        listBanners.add(item)
                    }
                }
                _banner.value = listBanners
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    fun getBrand() {
        val brandRef = firebaseDatabase.getReference("Category")
        _isLoading.value = true
        brandRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _isLoading.value = false
                val listBrand = mutableListOf<BrandModel>()
                for (snapshotChild in snapshot.children) {
                    val item = snapshotChild.getValue(BrandModel::class.java)
                    if (item != null) {
                        listBrand.add(item)
                    }
                }
                _brand.value = listBrand
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun getRecommend() {
        val brandRef = firebaseDatabase.getReference("Items")
        _isLoading.value = true
        brandRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _isLoading.value = false
                val listRecommend = mutableListOf<RecommendModel>()
                for (snapshotChild in snapshot.children) {
                    val item = snapshotChild.getValue(RecommendModel::class.java)
                    if (item != null) {
                        listRecommend.add(item)
                    }
                }
                _recommend.value = listRecommend
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}
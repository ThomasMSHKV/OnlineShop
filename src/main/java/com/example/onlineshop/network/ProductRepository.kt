package com.example.onlineshop.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class ProductRepository: CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private val productApi = Retrofit.Builder()
        .baseUrl("https://rstestapi.redsoftdigital.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)

    fun getOder() = async {
        productApi.getProduct()
            .execute()
            .body()
            ?.products
    }

    fun getOrderByTitle(title: String) = async {
        productApi.getProductByTitle(title)
            .execute()
            .body()
    }


    fun getOrderById(id:Int) = async {
        productApi.getProduct(id)
            .execute()
            .body()
            ?.product
    }
}
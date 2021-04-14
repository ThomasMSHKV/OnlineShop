package com.example.onlineshop.network

import com.google.gson.annotations.SerializedName

interface ApiShop {
    data class Shop(
        @SerializedName("data")
        val product: Product
    )
}
package com.example.onlineshop.network

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("data") val products: List<Product>

)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("short_description") val short_description: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("amount") val amount: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("producer") val producer: String,
    @SerializedName("categories") val categories: String

)

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("parent_id") val parent_id: Int
)
package com.example.onlineshop.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/api/v1/products")
    fun getProduct(): Call<Products>

    @GET("/api/v1/products")
    fun getProductByTitle(@Query("title")title: String): Call<Products>

    @GET("/api/v1/products/{productId}")
    fun getProduct(
        @Path("productId") productId: Int
    ): Call<ApiShop.Shop>
}
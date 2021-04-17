package com.example.onlineshop.orderList.view

import com.example.onlineshop.network.Product

interface OrderListContract {
    interface View {
        fun setData(products: List<Product>)
        fun replaceData(products: List<Product>)
    }

    interface Presenter {
        fun getProducts()
        fun getProductsByFilter(title: String)

    }

    interface Model {
        fun getProductList()
    }

}
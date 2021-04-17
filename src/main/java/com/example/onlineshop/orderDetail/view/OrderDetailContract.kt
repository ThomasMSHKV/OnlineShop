package com.example.onlineshop.orderDetail.view

import com.example.onlineshop.network.Product

interface OrderDetailContract {

    interface View {
        fun setData(product: Product)
    }
    interface Presenter{
        fun getProducts(id: Int)
    }
}
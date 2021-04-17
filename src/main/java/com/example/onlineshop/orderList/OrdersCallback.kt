package com.example.onlineshop.orderList

import com.example.onlineshop.network.Product

interface OrdersCallback {
    fun setList(list: List<Product>)
    fun openFragment(order_id:Int, number: Int)
}
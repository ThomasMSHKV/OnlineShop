package com.example.onlineshop.orderList.model

import com.example.onlineshop.network.Product
import com.example.onlineshop.network.ProductRepository
import com.example.onlineshop.orderDetail.view.OrderDetailContract
import com.example.onlineshop.orderList.view.OrderListContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderListModel: OrderListContract.Model {
    private val repository = ProductRepository()
    private var products: List<Product> = listOf()



    override fun getProductList() {
        GlobalScope.launch(Dispatchers.IO){
            products = repository.getOrder().await()!!
        }
    }


}
package com.example.onlineshop.orderDetail.view

import com.example.onlineshop.network.Product
import com.example.onlineshop.network.ProductRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class OrderDetailPresenter(val view: OrderDetailContract.View) : OrderDetailContract.Presenter,
    CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private val repository = ProductRepository()
    lateinit var product: Product

    override fun getProducts(id: Int) {

        GlobalScope.launch(Dispatchers.IO) {
            product = repository.getOrderById(id).await()!!

            withContext(Dispatchers.Main) {
                view.setData(product)
            }
        }
    }
}
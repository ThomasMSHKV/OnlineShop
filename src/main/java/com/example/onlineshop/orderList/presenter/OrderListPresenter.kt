package com.example.onlineshop.orderList.presenter

import com.example.onlineshop.network.Product
import com.example.onlineshop.network.ProductRepository
import com.example.onlineshop.orderList.view.OrderListContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class OrderListPresenter(val view: OrderListContract.View): OrderListContract.Presenter,
    CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private val repository = ProductRepository()
    private var products: List<Product>? = listOf()


    override fun getProducts(){
        GlobalScope.launch(Dispatchers.IO){
            products = repository.getOrder().await()!!
            withContext(Dispatchers.Main){
                view.setData(products!!)
            }
        }
    }

    override fun getProductsByFilter(title: String){
        GlobalScope.launch(Dispatchers.IO){


            val productsByTitle = repository.getOrderByTitle(title).await()

            withContext(Dispatchers.Main){
                productsByTitle?.let { view.replaceData(it.products) }
            }
        }
    }
}
package com.example.onlineshop.orderList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.network.Product
import com.example.onlineshop.orderList.OrdersCallback

class OrderListAdapter(private val callback: OrdersCallback) :
    RecyclerView.Adapter<OrderListAdapter.VHOrder>() {
    var orders = mutableListOf<Product>()
    var categoriesList = mutableListOf<String>()
    private var count: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOrder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        val holder = VHOrder(view)

        holder.image.setOnClickListener {
            callback.openFragment(orders[holder.adapterPosition].id, count)
        }
        holder.product.setOnClickListener {
            callback.openFragment(orders[holder.adapterPosition].id, count)
        }
        holder.store.setOnClickListener {
            holder.store.visibility = View.INVISIBLE
            holder.control.visibility = View.VISIBLE
            count += 1
            holder.number.text = count.toString()
        }
        holder.add.setOnClickListener {
            count++
            holder.number.text = count.toString()
        }
        holder.remove.setOnClickListener {
            count--
            holder.number.text = count.toString()
            if (count == 0) {
                holder.control.visibility = View.GONE
                holder.store.visibility = View.VISIBLE
            }
        }
        return holder
    }


    override fun onBindViewHolder(holder: VHOrder, position: Int) {
        Glide.with(holder.itemView).load(orders[position].image_url).into(holder.image)
        holder.product.text = orders[position].title
        holder.producer.text = orders[position].producer
        orders[position].categories.forEach {
            categoriesList.add(it.title)
        }
        if (categoriesList.isNullOrEmpty().not()){
            holder.category.text = categoriesList[0]
        }
        holder.product.text = orders[position].title
        holder.price.text = orders[position].price.toString()
    }

    override fun getItemCount(): Int {

        return orders.size
    }

    fun addList(products: List<Product>) {
        orders = products as MutableList<Product>
        notifyDataSetChanged()
    }

    fun replaceList(products: List<Product>) {
        orders.clear()
        orders.addAll(products)
        notifyDataSetChanged()
    }


    class VHOrder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.order_item_iv)
        val producer: TextView = itemView.findViewById(R.id.order_item_maker)
        val product: TextView = itemView.findViewById(R.id.order_item_product)
        val price: TextView = itemView.findViewById(R.id.order_item_price)
        val category: TextView = itemView.findViewById(R.id.order_item_category)
        val remove: ImageView = itemView.findViewById(R.id.remove_btn)
        val add: ImageView = itemView.findViewById(R.id.add_btn)
        val number: TextView = itemView.findViewById(R.id.number)
        val store: ImageView = itemView.findViewById(R.id.store_btn)
        val control: LinearLayout = itemView.findViewById(R.id.control)
    }
}
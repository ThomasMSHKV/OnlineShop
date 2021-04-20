package com.example.onlineshop.orderList.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLishshopBinding
import com.example.onlineshop.databinding.FragmentShopDetailBinding
import com.example.onlineshop.network.Product
import com.example.onlineshop.orderDetail.view.OrderDetailFragments
import com.example.onlineshop.orderList.OrdersCallback
import com.example.onlineshop.orderList.adapter.OrderListAdapter
import com.example.onlineshop.orderList.presenter.OrderListPresenter


class ListShopFragment : Fragment(R.layout.fragment_lishshop), OrderListContract.View {
    private var _binding: FragmentLishshopBinding? = null
    private val binding get() = _binding!!
    private val presenter = OrderListPresenter(this)
    lateinit var adapter: OrderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLishshopBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    fun init() {
        adapter = OrderListAdapter(callback)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        presenter.getProducts()
        binding.etFilter.doAfterTextChanged {
            if (it.isNullOrEmpty().not()) {
                presenter.getProductsByFilter(it.toString())
                adapter.notifyDataSetChanged()
            }
        }
        binding.arrowBack.setOnClickListener {
            binding.etFilter.text.clear()
        }
    }

    override fun setData(products: List<Product>) {
        adapter.addList(products)
    }

    override fun replaceData(products: List<Product>) {
        Log.i("list", products.size.toString())
        adapter.replaceList(products)
    }

    val callback = object : OrdersCallback {
        override fun setList(list: List<Product>) {

        }

        override fun openFragment(order_id: Int, number: Int) {
            val fragment = OrderDetailFragments()
            val bundle = Bundle()
            bundle.putInt("key", order_id)
            bundle.putInt("number", number)
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}





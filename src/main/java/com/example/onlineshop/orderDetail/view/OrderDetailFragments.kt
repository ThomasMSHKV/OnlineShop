package com.example.onlineshop.orderDetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentShopDetailBinding
import com.example.onlineshop.network.Product
import java.lang.StringBuilder

class OrderDetailFragments : Fragment(R.layout.fragment_shop_detail), OrderDetailContract.View {
    private var _binding: FragmentShopDetailBinding? = null
    private val binding get() = _binding!!
    private val presenter = OrderDetailPresenter(this)
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        count = arguments?.getInt("number")!!
        init()
        presenter.getProducts(arguments?.getInt("key")!!)

    }

    private fun init() {
        binding.numberDetail.text = count.toString()
        binding.arrowBackDetail.setOnClickListener {
            requireActivity().onBackPressed()
        }
        if (count > 0) {
            binding.controlDetail.visibility = View.VISIBLE
            binding.storeBtnDetail.visibility = View.GONE
            count += 1
            binding.numberDetail.text = count.toString()
        }
        binding.addBtnDetail.setOnClickListener {
            count++
            binding.numberDetail.text = count.toString()
        }
        binding.removeBtnDetail.setOnClickListener {
            count--
            binding.numberDetail.text = count.toString()
            if (count == 0) {
                binding.controlDetail.visibility = View.GONE
                binding.storeBtnDetail.visibility = View.VISIBLE
            }
        }
    }


    override fun setData(product: Product) {
        val builder = StringBuilder()
        product.categories.forEach {
            builder.append("${it.title} , ")
        }
        binding.orderDetailCategory.text = builder
        binding.orderDetailDescription.text = product.short_description
        binding.orderDetailPrice.text = product.price.toString()
        binding.orderDetailMaker.text = product.producer
        binding.orderDetailProduct.text = product.title
        binding.numberDetail.text = count.toString()

        Glide.with(requireContext()).load(product.image_url).centerCrop().into(order_detail_iv)

    }
}
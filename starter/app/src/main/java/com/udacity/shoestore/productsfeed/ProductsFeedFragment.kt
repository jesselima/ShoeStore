package com.udacity.shoestore.productsfeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentProductsFeedBinding
import com.udacity.shoestore.login.LoginFragmentDirections


class ProductsFeedFragment : Fragment() {

    private lateinit var binding: FragmentProductsFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_products_feed,
            container,
            false
        )
        setupViewModel()
        setupObservers()
        setupListeners()
        return binding.root
    }

    private fun setupViewModel() {
        // TODO("not implemented")
    }

    private fun setupObservers() {
        // TODO("not implemented")
    }

    private fun setupListeners() {
        binding.buttonActionProductsFeed.setOnClickListener {
            findNavController().navigate(
                ProductsFeedFragmentDirections.actionProductsFeedFragmentToProductDetailsFragment()
            )
        }
    }
}
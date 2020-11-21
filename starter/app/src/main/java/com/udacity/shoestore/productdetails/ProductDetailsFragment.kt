package com.udacity.shoestore.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.core.extensions.showWithFadeIn
import com.udacity.shoestore.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_details,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productDetailsImageBackAction.showWithFadeIn()
        setupViewModel()
        setupObservers()
        setupListeners()
    }

    private fun setupViewModel() {
        // TODO("not implemented")
    }

    private fun setupObservers() {
        // TODO("not implemented")
    }

    private fun setupListeners() {
        binding.buttonActionFromProductDetails.setOnClickListener {
            binding.productDetailsImageBackAction.visibility = View.GONE
            findNavController().navigate(
                ProductDetailsFragmentDirections.actionProductDetailsFragmentToProductsFeedFragment()
            )
        }
        binding.productDetailsImageBackAction.setOnClickListener {
            it.visibility = View.GONE
            findNavController().navigate(
                    ProductDetailsFragmentDirections.actionProductDetailsFragmentToProductsFeedFragment()
            )
        }
    }
}
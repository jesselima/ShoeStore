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
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModel<ProductDetailsViewModel>()

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
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.shoeLiveData.observe(viewLifecycleOwner, { shoe ->
            Timber.i(shoe.toString())
        })
    }

    private fun setupListeners() {
        binding.buttonActionFromProductDetails.setOnClickListener {
            binding.productDetailsImageBackAction.visibility = View.GONE
            findNavController().navigate(
                ProductDetailsFragmentDirections.navigateBackToProductsFeed()
            )
        }
        binding.productDetailsImageBackAction.setOnClickListener {
            it.visibility = View.GONE
            findNavController().navigate(
                    ProductDetailsFragmentDirections.navigateBackToProductsFeed()
            )
        }
    }
}
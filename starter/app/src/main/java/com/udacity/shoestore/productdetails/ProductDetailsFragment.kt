package com.udacity.shoestore.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.core.KeyValues
import com.udacity.shoestore.databinding.FragmentProductDetailsBinding
import com.udacity.shoestore.sharedpresentation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel by viewModel<ProductDetailsViewModel>()
    private val sharedViewModel by viewModel<SharedViewModel>()

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        val shoe = arguments?.getInt(KeyValues.KEY_SHOE)
        shoe?.let {
            sharedViewModel.sharedShoeId.value = it
        }
    }

    private fun setupObservers() {
        sharedViewModel.sharedShoeId.observe(viewLifecycleOwner, { shoeId ->
            viewModel.getShoeById(id = shoeId)
        })
    }

    private fun setupListeners() {
        binding.detailsButtonEdit.setOnClickListener {
            binding.viewModel?.shoe?.let {
                val bundle = bundleOf("KEY_SHOE" to it.value)
                findNavController().navigate(R.id.navigateToProductEditor, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.sharedShoeId.value?.let {
            viewModel.getShoeById(it)
        }
    }
}
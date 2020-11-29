package com.udacity.shoestore.producteditor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentProductsFeedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ProductEditorFragment : Fragment() {

    private lateinit var binding: FragmentProductsFeedBinding
    private val viewModel by viewModel<ProductEditorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_editor,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.shoesLiveData.observe(viewLifecycleOwner, { shoe ->
            Timber.i(shoe.toString())
        })
        viewModel.isShoeSavedLiveData.observe(viewLifecycleOwner, { isShoeSaved ->
            Timber.i(isShoeSaved.toString())
        })
        viewModel.isShoeUpdatedLiveData.observe(viewLifecycleOwner, { isShoeUpdated ->
            Timber.i(isShoeUpdated.toString())
        })
    }

    fun getCurrentProduct() {
        //val id = ProductEditorFragmentArgs
    }

    private fun setupListeners() {
//        binding.buttonActionProductsFeed.setOnClickListener {
//            findNavController().navigate(
//                ProductsFeedFragmentDirections.actionProductsFeedFragmentToProductDetailsFragment()
//            )
//        }
    }
}
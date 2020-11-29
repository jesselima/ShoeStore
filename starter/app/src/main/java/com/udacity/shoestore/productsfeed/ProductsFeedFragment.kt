package com.udacity.shoestore.productsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentProductsFeedBinding
import com.udacity.shoestore.sharedpresentation.adapter.ShoeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductsFeedFragment : Fragment() {

    private lateinit var binding: FragmentProductsFeedBinding
    private val viewModel by viewModel<ProductsFeedViewModel>()
    private var adapterHotSelling = ShoeAdapter()
    private var adapterShoes = ShoeAdapter()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerViewForHotSelling()
        setupRecyclerViewForShoesExceptHotSelling()
    }

    private fun setupRecyclerViewForHotSelling() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerShoesHotSelling.layoutManager = linearLayoutManager
        binding.recyclerShoesHotSelling.adapter = adapterHotSelling
    }

    private fun setupRecyclerViewForShoesExceptHotSelling() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewShoesExceptHotSelling.layoutManager = linearLayoutManager
        binding.recyclerViewShoesExceptHotSelling.adapter = adapterShoes
    }

    private fun setupObservers() {
        viewModel.allShoesExceptHotSellingLiveData.observe(viewLifecycleOwner, { allShoes ->
            adapterShoes.submitList(allShoes)
        })
        viewModel.hotSellingShoesLiveData.observe(viewLifecycleOwner, { hotSellingShoes ->
            adapterHotSelling.submitList(hotSellingShoes)
        })
    }
}
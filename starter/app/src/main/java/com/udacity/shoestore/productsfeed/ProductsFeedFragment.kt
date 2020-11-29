package com.udacity.shoestore.productsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentProductsFeedBinding
import com.udacity.shoestore.sharedpresentation.adapter.ShoeAdapter
import kotlinx.android.synthetic.main.fragment_products_feed.*
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
        setupListeners()
        setupRecyclerViewForHotSelling()
        setupRecyclerViewForShoesExceptHotSelling()
    }

    private fun setupListeners() {
        buttonActionGoToEditor.setOnClickListener {
            findNavController().navigate(R.id.navigateToProductEditor)
        }
    }

    private fun setupRecyclerViewForHotSelling() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerShoesHotSelling.layoutManager = linearLayoutManager
        binding.recyclerShoesHotSelling.adapter = adapterHotSelling
    }

    private fun setupRecyclerViewForShoesExceptHotSelling() {
        binding.recyclerViewShoesGeneralList.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.recyclerViewShoesGeneralList.adapter = adapterShoes
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            feedLoadingIndicator.isVisible = isLoading
            if (isLoading) {
                feedLoadingIndicator.playAnimation()
            } else {
                feedLoadingIndicator.cancelAnimation()
            }
        })

        viewModel.allShoesLiveData.observe(viewLifecycleOwner, { allShoes ->
            groupFeedMainContent.isVisible = allShoes.isNotEmpty()
            groupFeedNoShoesFound.isVisible = allShoes.isEmpty()
            adapterShoes.submitList(allShoes)
        })
        viewModel.hotSellingShoesLiveData.observe(viewLifecycleOwner, { hotSellingShoes ->
            recyclerShoesHotSelling.isVisible = hotSellingShoes.isEmpty()
            labelTopSellers.isVisible = hotSellingShoes.isNotEmpty()
            adapterHotSelling.submitList(hotSellingShoes)
        })
    }
}
package com.udacity.shoestore.productsfeed

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.core.extensions.showDialogWithOptionalActions
import com.udacity.shoestore.databinding.FragmentProductsFeedBinding
import com.udacity.shoestore.sharedpresentation.adapter.ShoeAdapter
import kotlinx.android.synthetic.main.fragment_products_feed.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductsFeedFragment : Fragment() {

    private lateinit var binding: FragmentProductsFeedBinding
    private val viewModel by viewModel<ProductsFeedViewModel>()
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        setupRecyclerViewForAllShoes()
    }

    private fun setupListeners() {
        feedButtonAddNewShoe.setOnClickListener {
            findNavController().navigate(R.id.navigateFromFeedToShoeEditor)
        }
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuActionLogout -> {
                    showLogoutConfirmationDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerViewForAllShoes() {
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
            recyclerViewShoesGeneralList.isVisible = allShoes.isNotEmpty()
            groupFeedNoShoesFound.isVisible = allShoes.isEmpty()
            adapterShoes.submitList(allShoes)
        })
    }

    private fun showLogoutConfirmationDialog() {
        context?.let {
            showDialogWithOptionalActions(
                context = it,
                title = getString(R.string.message_logout),
                positiveButtonText = getString(R.string.label_logout),
                positiveButtonAction = {
                    logout()
                }
            )
        }
    }
    private fun logout() {
        findNavController().navigate(R.id.navigateFromFeedToLogin)
        viewModel.logout()
    }
}
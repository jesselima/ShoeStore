package com.udacity.shoestore.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.core.KeyValues
import com.udacity.shoestore.core.extensions.mapStringToImageResource
import com.udacity.shoestore.core.extensions.showDialogWithOptionalActions
import com.udacity.shoestore.databinding.FragmentProductDetailsBinding
import com.udacity.shoestore.sharedpresentation.SharedViewModel
import kotlinx.android.synthetic.main.fragment_product_details.*
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
        val shoe = arguments?.getLong(KeyValues.KEY_SHOE)
        shoe?.let {
            sharedViewModel.sharedShoeId.value = it
        }
    }

    private fun setupObservers() {
        sharedViewModel.sharedShoeId.observe(viewLifecycleOwner, { shoeId ->
            viewModel.getShoeById(id = shoeId.toInt())
        })
        viewModel.shoe.observe(viewLifecycleOwner, { shoe ->
            shoe?.isHotSelling?.let {
                groupShoeEditorTopSeller.isVisible = it
            }
            shoe?.image?.let {
                detailsImageShoe.setImageResource(it.mapStringToImageResource())
            }
        })
        viewModel.isShoeDeleted.observe(viewLifecycleOwner, { isDeleted ->
            if (isDeleted) {
                Toast.makeText(context, getString(R.string.message_item_deleted), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.navigateBackToProductsFeed)
            }
        })
    }

    private fun setupListeners() {
        binding.detailsButtonEdit.setOnClickListener {
            binding.viewModel?.shoe?.let {
                val bundle = bundleOf(KeyValues.KEY_SHOE to it.value)
                findNavController().navigate(R.id.navigateToProductEditor, bundle)
            }
        }
        binding.toolbarShoeDetails.setNavigationOnClickListener {
            findNavController().navigate(R.id.navigateBackToProductsFeed)
        }
        detailsButtonDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        context?.let {
            showDialogWithOptionalActions(
                context = it,
                title = getString(R.string.attention),
                message = getString(R.string.message_confirm_delete_action),
                positiveButtonText = getString(R.string.label_delete),
                positiveButtonAction = { viewModel.deleteShoe() }
            )
        }
    }
}
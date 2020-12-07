package com.udacity.shoestore.producteditor

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
import com.udacity.shoestore.core.extensions.getRandomImageKey
import com.udacity.shoestore.databinding.FragmentProductEditorBinding
import com.udacity.shoestore.sharedpresentation.SharedViewModel
import kotlinx.android.synthetic.main.fragment_product_editor.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.jesselima.local.sqlite.data.shoes.models.Shoe


class ProductEditorFragment : Fragment() {

    private lateinit var binding: FragmentProductEditorBinding
    private val viewModel by viewModel<ProductEditorViewModel>()
    private val sharedViewModel by viewModel<SharedViewModel>()

    private var shoe: Shoe? = null
    private var isEditing = false

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        shoe = arguments?.getParcelable(KeyValues.KEY_SHOE) as Shoe?
        viewModel.shoe.value = shoe

        isEditing = shoe != null

        if(isEditing) {
            buttonSaveOrCreateShoe.text = getString(R.string.label_update)
            textEditorLabelTile.text = getString(R.string.update_shoe)
        } else {
            textEditorLabelTile.text = getString(R.string.created_shoe)
            buttonSaveOrCreateShoe.text = getString(R.string.save)
        }
     }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            loadingSaveOrUpdate.isVisible = isLoading
        })
        viewModel.newShoeId.observe(viewLifecycleOwner, { newShoeId ->
            sharedViewModel.sharedShoeId.value = newShoeId.toLong()
            val bundle = bundleOf(KeyValues.KEY_SHOE to newShoeId)
            findNavController().navigate(R.id.navigateBackToShoeDetails, bundle)
        })
        viewModel.isShoeUpdated.observe(viewLifecycleOwner, { isShoeUpdated ->
            if (isShoeUpdated) {
                Toast.makeText(context, getString(R.string.message_item_updated), Toast.LENGTH_SHORT).show()
                val bundle = bundleOf(KeyValues.KEY_SHOE to shoe?.id?.toLong())
                findNavController().navigate(R.id.navigateBackToShoeDetails, bundle)
            } else {
                Toast.makeText(context, getString(R.string.message_something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupListeners() {
        toolbarShoeEditor.setNavigationOnClickListener {
            if(isEditing) {
                findNavController().navigate(
                    R.id.navigateBackToShoeDetails,
                    bundleOf(KeyValues.KEY_SHOE to shoe?.id?.toLong())
                )
            } else {
                findNavController().navigate(R.id.navigateToProductsFeed)
            }
        }

        buttonCancelEdit.setOnClickListener {
            if (shoe?.id == null) {
                findNavController().navigate(R.id.navigateToProductsFeed)
            } else {
                findNavController().navigate(
                    R.id.navigateBackToShoeDetails,
                    bundleOf(KeyValues.KEY_SHOE to shoe?.id?.toLong())
                )
            }
        }

        buttonSaveOrCreateShoe.setOnClickListener {
            val name = editTextName.text.toString()
            val brand = editTextBrand.text.toString()
            val price = formatPrice(editTextPrice.text.toString())
            val season = editTextSeason.text.toString()
            val year = editTextYear.text.toString().toInt()
            val category = editTextCategory.text.toString()
            val isHotSelling = switchIsHotSelling.isChecked
            val stockQuantity = formatQuantity(editTextStockQuantity.text.toString())
            val quantitySold = formatQuantity(editTextQuantitySold.text.toString())
            val image = getRandomImageKey()

            if (shoe != null) {
                shoe?.id?.let { shoeId ->
                    viewModel.updateCurrentShoe(
                        Shoe(id = shoeId,
                            name = name,
                            brand = brand,
                            price = price,
                            season = season,
                            year = year,
                            category = category,
                            isHotSelling = isHotSelling,
                            stockQuantity = stockQuantity,
                            quantitySold = quantitySold,
                            image = image
                        )
                    )
                }
            } else {
                viewModel.saveNewShoe(
                    Shoe(
                        name = name,
                        brand = brand,
                        price = price,
                        season = season,
                        year = year,
                        category = category,
                        isHotSelling = isHotSelling,
                        stockQuantity = stockQuantity,
                        quantitySold = quantitySold,
                        image = image
                    )
                )
            }
        }
    }
}

fun formatQuantity(value: String?): Int {
    return value?.toInt() ?: 0
}

fun formatPrice(value: String?): Float {
    return if (value != null) {
        String.format("%.3f", value.toFloat()).toFloat()
    } else {
        0f
    }
}
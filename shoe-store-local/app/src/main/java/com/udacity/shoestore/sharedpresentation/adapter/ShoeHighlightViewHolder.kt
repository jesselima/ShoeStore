package com.udacity.shoestore.sharedpresentation.adapter

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.core.extensions.mapStringToImageResource
import kotlinx.android.synthetic.main.item_list_shoe_highlight.view.*
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

private const val KEY_SHOE = "KEY_SHOE"

class ShoeHighlightViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var shoe: Shoe? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val bundle = bundleOf(KEY_SHOE to shoe?.id?.toLong())
        view.findNavController().navigate(R.id.navigateToProductDetails, bundle)
    }

    fun bindDataToView(shoe: Shoe) {
        this.shoe = shoe
        view.textShoeNameHotSelling.text = shoe.name
        view.textShoeBrandHotSelling.text = shoe.brand
        view.textShoePriceHotSelling.text =  view.context.getString(R.string.price_format, String.format("%.2f", shoe.price))
        shoe.image?.let {
            view.imageShoeHighlightedItem.setImageResource(it.mapStringToImageResource())
        }
    }
}
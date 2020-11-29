package com.udacity.shoestore.sharedpresentation.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.productsfeed.ProductsFeedFragmentDirections
import kotlinx.android.synthetic.main.item_list_shoe_highlight.view.*
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

class ShoeHighlightViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var shoe: Shoe? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        view.findNavController()
            .navigate(
                ProductsFeedFragmentDirections.navigateToProductDetails()
            )
    }

    fun bindDataToView(shoe: Shoe) {
        this.shoe = shoe
        view.textShoeNameHotSelling.text = shoe.name
        view.textShoeBrandHotSelling.text = shoe.brand
        view.textShoePriceHotSelling.text = shoe.price.toString()
    }
}
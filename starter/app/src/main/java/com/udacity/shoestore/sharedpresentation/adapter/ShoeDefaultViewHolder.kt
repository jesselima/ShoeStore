package com.udacity.shoestore.sharedpresentation.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.productsfeed.ProductsFeedFragmentDirections
import kotlinx.android.synthetic.main.item_list_shoe.view.*
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

class ShoeDefaultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var shoe: Shoe? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        view.findNavController()
            .navigate(
                    ProductsFeedFragmentDirections.actionProductsFeedFragmentToProductDetailsFragment()
            )
    }

    fun bindDataToView(shoe: Shoe) {
        this.shoe = shoe
        view.textShoeName.text = shoe.name
        view.textShoeBrand.text = shoe.name
        view.textShoePrice.text = shoe.price.toString()
    }
}
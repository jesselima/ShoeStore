package com.udacity.shoestore.sharedpresentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.core.extensions.inflate
import com.udacity.shoestore.core.extensions.showListItemWithFadeIn
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

private const val VIEW_TYPE_FOR_HOT_SELLING = 0
private const val VIEW_TYPE_FOR_DEFAULT_LIST = 1

class ShoeAdapter(
    private var shoes: List<Shoe> = emptyList()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun submitList(newShoes: List<Shoe>) {
        shoes = newShoes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        rootViewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FOR_HOT_SELLING -> {
                rootViewGroup.inflate(R.layout.item_list_shoe_highlight).run {
                    showListItemWithFadeIn()
                    ShoeHighlightViewHolder(this)
                }
            }
            else -> {
                rootViewGroup.inflate(R.layout.item_list_shoe).run {
                    showListItemWithFadeIn()
                    ShoeDefaultViewHolder(
                        this
                    )
                }
            }
        }
    }

    override fun getItemCount() = shoes.size

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        val shoe = shoes[position]
        return if (shoe.isHotSelling) {
            VIEW_TYPE_FOR_HOT_SELLING
        } else {
            VIEW_TYPE_FOR_DEFAULT_LIST
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val shoe = shoes[position]
        if (shoe.isHotSelling) {
            val shoeViewHolder = holder as ShoeHighlightViewHolder
            shoeViewHolder.bindDataToView(shoe)
        } else {
            val shoeHighlightViewHolder = holder as ShoeDefaultViewHolder
            shoeHighlightViewHolder.bindDataToView(shoe)
        }
    }
}
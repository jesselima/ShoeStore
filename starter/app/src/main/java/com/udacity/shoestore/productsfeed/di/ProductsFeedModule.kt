package com.udacity.shoestore.productsfeed.di

import com.udacity.shoestore.productdetails.ProductDetailsViewModel
import com.udacity.shoestore.productsfeed.ProductsFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object ProductsFeedModule {
   private val productFeedModule = module {
        viewModel {
            ProductsFeedViewModel(shoesLocalRepository = get())
        }
    }

    fun loadModuleDependency() = productFeedModule
}
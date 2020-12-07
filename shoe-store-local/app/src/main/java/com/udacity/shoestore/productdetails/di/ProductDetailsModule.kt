package com.udacity.shoestore.productdetails.di

import com.udacity.shoestore.productdetails.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object ProductDetailsModule {
   private val productDetailsModule = module {
        viewModel {
            ProductDetailsViewModel(shoesLocalRepository = get())
        }
    }

    fun loadModuleDependency() = productDetailsModule
}
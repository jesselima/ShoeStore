package com.udacity.shoestore.producteditor.di

import com.udacity.shoestore.producteditor.ProductEditorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object ProductEditorModule {
   private val productEditorModule = module {
        viewModel {
            ProductEditorViewModel(shoesLocalRepository = get())
        }
    }

    fun loadModuleDependency() = productEditorModule
}
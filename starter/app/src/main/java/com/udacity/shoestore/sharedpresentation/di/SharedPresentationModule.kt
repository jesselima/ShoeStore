package com.udacity.shoestore.sharedpresentation.di

import com.udacity.shoestore.sharedpresentation.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by jesselima on 29/11/20.
 * This is a part of the project ShoeStore.
 */
object SharedPresentationModule {
    private val sharedPresentationModule = module {
        viewModel {
            SharedViewModel()
        }
    }
    fun loadModuleDependency() = sharedPresentationModule
}
package com.udacity.shoestore.login.di

import com.udacity.shoestore.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object LoginModule {
   private val loginModule = module {
        viewModel {
            LoginViewModel(
                shoesLocalRepository = get(),
                sharedPrefUserStorage = get()
            )
        }
    }

    fun loadModuleDependency() = loginModule
}
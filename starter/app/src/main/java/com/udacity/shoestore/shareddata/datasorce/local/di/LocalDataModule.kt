package com.udacity.shoestore.shareddata.datasorce.local.di

import com.udacity.shoestore.shareddata.datasorce.local.repository.ShoesLocalRepository
import com.udacity.shoestore.shareddata.datasorce.local.repository.ShoesLocalRepositoryImpl
import org.koin.dsl.module

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object LocalDataModule {

    private val localDataModule = module {
        single<ShoesLocalRepository> {
            ShoesLocalRepositoryImpl(
                shoesLocalDataSource = get()
            )
        }
    }

    fun loadModuleDependency() = localDataModule
}
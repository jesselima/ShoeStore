package com.udacity.shoestore.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import com.udacity.shoestore.shareddata.datasorce.local.di.LocalDataModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.udacity.shoestore.login.di.LoginModule
import com.udacity.shoestore.productsfeed.di.ProductsFeedModule
import com.udacity.shoestore.productdetails.di.ProductDetailsModule
import tech.jesselima.local.gson.GsonModule
import tech.jesselima.local.sharedpref.di.SharedPrefModule
import tech.jesselima.local.sqlite.data.shoes.di.ShoeLocalDatabaseModule


internal class GlobalInjectableDependencies(
    private val applicationContext: Context
) {
    fun initKoin() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    GsonModule.loadModuleDependency(),
                    ShoeLocalDatabaseModule.loadModuleDependency(),
                    LocalDataModule.loadModuleDependency(),
                    LoginModule.loadModuleDependency(),
                    ProductsFeedModule.loadModuleDependency(),
                    ProductDetailsModule.loadModuleDependency(),
                    SharedPrefModule.loadModuleDependency(),
                )
            )
        }
    }
}


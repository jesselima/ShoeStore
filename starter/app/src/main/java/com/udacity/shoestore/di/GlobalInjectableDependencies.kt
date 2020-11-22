package com.udacity.shoestore.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
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
                    SharedPrefModule.loadModuleDependency(),
                )
            )
        }
    }
}


package com.udacity.shoestore.core

import android.app.Application
import timber.log.Timber

/**
 * Created by jesselima on 15/11/20.
 * This is a part of the project ShoeStore.
 */
class ShoeStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
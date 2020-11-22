package tech.jesselima.local.gson

import com.google.gson.GsonBuilder
import org.koin.dsl.module

object GsonModule {

    private val gsonModule = module {
        single { GsonBuilder().setLenient().create() }
    }

    fun loadModuleDependency() = gsonModule
}
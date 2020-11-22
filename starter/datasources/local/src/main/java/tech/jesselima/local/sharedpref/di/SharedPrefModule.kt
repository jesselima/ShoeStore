package tech.jesselima.local.sharedpref.di

import android.content.Context
import org.koin.dsl.module
import tech.jesselima.local.sharedpref.data.SharedPrefUserStorageImpl
import tech.jesselima.local.sharedpref.data.SharedPrefUserStorage

object SharedPrefModule {

    private val sharedPrefsModule = module {

        fun prefs(context: Context) = context.getSharedPreferences(
            "USER_PREFS",
            Context.MODE_PRIVATE
        )
        single { prefs(get()) }
        single<SharedPrefUserStorage> {
            SharedPrefUserStorageImpl(
                prefs = get(),
                gson = get()
            )
        }
    }

    fun loadModuleDependency() = sharedPrefsModule

}
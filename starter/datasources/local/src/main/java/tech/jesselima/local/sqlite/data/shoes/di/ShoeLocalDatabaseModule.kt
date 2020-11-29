package tech.jesselima.local.sqlite.data.shoes.di

import org.koin.dsl.module
import tech.jesselima.local.sqlite.data.shoes.datasource.ShoesLocalDataSource
import tech.jesselima.local.sqlite.data.shoes.datasource.ShoesLocalDataSourceImpl

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object ShoeLocalDatabaseModule {

    private val shoeLocalDatabaseModuleModule = module {
        single<ShoesLocalDataSource> {
            ShoesLocalDataSourceImpl(
                application = get()
            )
        }
    }

    fun loadModuleDependency() = shoeLocalDatabaseModuleModule
}
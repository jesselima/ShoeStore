package tech.jesselima.local.sqlite.data.shoes.di

import org.koin.dsl.module
import tech.jesselima.local.sqlite.data.shoes.repository.ShoesLocalRepository
import tech.jesselima.local.sqlite.data.shoes.repository.ShoesLocalRepositoryImpl

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
object ShoeLocalDatabaseModule {

    private val shoeLocalDatabaseModuleModule = module {
        single<ShoesLocalRepository> {
            ShoesLocalRepositoryImpl(
                application = get()
            )
        }
    }

    fun loadModuleDependency() = shoeLocalDatabaseModuleModule
}
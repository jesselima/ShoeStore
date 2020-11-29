package com.udacity.shoestore.shareddata.datasorce.local.repository

import tech.jesselima.local.sqlite.data.shoes.models.Shoe
import tech.jesselima.local.sqlite.data.shoes.datasource.ShoesLocalDataSource

class ShoesLocalRepositoryImpl(
    private val shoesLocalDataSource: ShoesLocalDataSource
): ShoesLocalRepository {
 
    override suspend fun getAllShoes(): List<Shoe> {
        return shoesLocalDataSource.getAllShoes()
    }

    override suspend fun getShoeById(id: Int): Shoe {
        return shoesLocalDataSource.getShoeById(id)
    }

    override suspend fun getShoesByCategory(category: String) : List<Shoe> {
        return shoesLocalDataSource.getShoesByCategory(category)
    }

    override suspend fun getHotSellingShoes() : List<Shoe> {
        return shoesLocalDataSource.getHotSellingShoes()
    }

    override suspend fun getAllShoesExceptHotSelling() : List<Shoe> {
        return shoesLocalDataSource.getAllShoesExceptHotSelling()
    }

    override suspend fun insertShoe(shoe: Shoe): Long {
        return shoesLocalDataSource.insertShoe(shoe)
    }

    override suspend fun updateShoe(shoe: Shoe): Int {
        return shoesLocalDataSource.updateShoe(shoe)
    }

    override suspend fun deleteShoe(shoe: Shoe): Int {
        return shoesLocalDataSource.deleteShoe(shoe)
    }
}
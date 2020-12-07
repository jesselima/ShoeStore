package com.udacity.shoestore.shareddata.datasorce.local.repository

import tech.jesselima.local.sqlite.data.shoes.models.Shoe

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
interface ShoesLocalRepository {

    suspend fun getAllShoes(): List<Shoe>

    suspend fun insertShoe(shoe: Shoe): Long

    suspend fun updateShoe(shoe: Shoe): Int

    suspend fun deleteShoe(shoe: Shoe): Int

    suspend fun getShoeById(id: Int): Shoe

    suspend fun getShoesByCategory(category: String) : List<Shoe>

    suspend fun getHotSellingShoes() : List<Shoe>

    suspend fun getAllShoesExceptHotSelling() : List<Shoe>

}
package tech.jesselima.local.sqlite.data.shoes.repository

import tech.jesselima.local.sqlite.data.shoes.models.Shoe

/**
 * Created by jesselima on 22/11/20.
 * This is a part of the project ShoeStore.
 */
interface ShoesLocalRepository {

    suspend fun getAllShoes(): List<Shoe>

    suspend fun insertShoe(shoe: Shoe): Long

    suspend fun deleteShoe(shoe: Shoe): Int

    suspend fun getShoeById(id: String): Shoe

    suspend fun getShoesByCategory(category: String) : List<Shoe>

    suspend fun getHotSellingShoes() : List<Shoe>

}
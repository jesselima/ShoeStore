package tech.jesselima.local.sqlite.data.shoes.models

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import androidx.room.Update

@Dao
interface ShoeDao {

    @Query("SELECT * FROM shoes")
    suspend fun getAllShoes(): List<Shoe>

    @Query("SELECT * FROM shoes WHERE id = :id")
    suspend fun getShoeById(id: Int): Shoe

    @Query("SELECT * FROM shoes WHERE category = :category")
    suspend fun getShoesByCategory(category: String): List<Shoe>

    @Query("SELECT * FROM shoes WHERE is_hot_selling = :isHotSelling")
    suspend fun getHotSellingShoes(isHotSelling: Boolean = true): List<Shoe>

    @Query("SELECT * FROM shoes WHERE is_hot_selling = :isHotSelling")
    suspend fun getAllShoesExceptHotSelling(isHotSelling: Boolean = false): List<Shoe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoe(shoe: Shoe): Long

    @Update
    suspend fun updateShoe(shoe: Shoe): Int

    @Delete
    suspend fun deleteShoe(shoe: Shoe): Int
}
package tech.jesselima.local.sqlite.data.shoes.datasource

import android.app.Application
import tech.jesselima.local.sqlite.data.AppDatabase
import tech.jesselima.local.sqlite.data.shoes.models.Shoe

class ShoesLocalDataSourceImpl(
    application: Application
): ShoesLocalDataSource {

    private val shoeDao = AppDatabase.getDatabase(application).shoesDao()

    override suspend fun getAllShoes(): List<Shoe> {
        return shoeDao.getAllShoes()
    }

    override suspend fun getShoeById(id: Int): Shoe {
        return shoeDao.getShoeById(id)
    }

    override suspend fun getShoesByCategory(category: String) : List<Shoe> {
        return shoeDao.getShoesByCategory(category)
    }

    override suspend fun getHotSellingShoes() : List<Shoe> {
        return shoeDao.getHotSellingShoes()
    }

    override suspend fun getAllShoesExceptHotSelling() : List<Shoe> {
        return shoeDao.getAllShoesExceptHotSelling()
    }

    override suspend fun insertShoe(shoe: Shoe): Long {
        return shoeDao.insertShoe(shoe)
    }

    override suspend fun updateShoe(shoe: Shoe): Int {
        return shoeDao.updateShoe(shoe)
    }

    override suspend fun deleteShoe(shoe: Shoe): Int {
        return shoeDao.deleteShoe(shoe)
    }
}
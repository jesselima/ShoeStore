package tech.jesselima.local.sqlite.data.shoes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoes")
data class Shoe(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "brand")
    val brand: String? = null,

    @ColumnInfo(name = "price")
    val price: Float? = null,

    @ColumnInfo(name = "season")
    val season: String? = null,

    @ColumnInfo(name = "year")
    val year: Int? = null,

    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "is_hot_selling")
    val isHotSelling: Boolean = false,

    @ColumnInfo(name = "stock_quantity")
    val stockQuantity: Int? = null,

    @ColumnInfo(name = "quantity_sold")
    val quantitySold: Int? = null,

    @ColumnInfo(name = "image")
    val image: String? = null

)
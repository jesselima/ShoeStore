package tech.jesselima.local.sqlite.data.shoes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoes")
data class Shoe(

    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int,

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
    val isHotSelling: Boolean? = null,

    @ColumnInfo(name = "stock_quantity")
    val stockQuantity: Boolean? = null,

    @ColumnInfo(name = "quantity_sold")
    val quantitySold: Boolean? = null
)
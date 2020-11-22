package tech.jesselima.local.sqlite.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.jesselima.local.sqlite.data.shoes.models.Shoe
import tech.jesselima.local.sqlite.data.shoes.models.ShoeDao

@Database(
    entities = [
        Shoe::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoesDao(): ShoeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shoe_store"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

package knu.mus.cryptocurrency_exchange_rate_viewer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 2, exportSchema = false)
abstract class CoinItemsDatabase: RoomDatabase() {
    abstract fun coinItemDao(): CoinItemDao

    companion object {
        @Volatile
        private var INSTANCE: CoinItemsDatabase? = null

        fun getDatabase(context: Context): CoinItemsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinItemsDatabase::class.java,
                    "coin_items_database"
                )
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance

                instance
            }
        }
    }
}

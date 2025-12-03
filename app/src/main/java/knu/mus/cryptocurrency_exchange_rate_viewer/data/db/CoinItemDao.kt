package knu.mus.cryptocurrency_exchange_rate_viewer.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
public interface CoinItemDao {
    @Query("SELECT * FROM coins_table ORDER BY price DESC")
    fun getEntities(): LiveData<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: CoinEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(items: List<CoinEntity>)

    @Delete
    suspend fun removeItem(item: CoinEntity)

    @Update
    suspend fun changeItem(item: CoinEntity)
}

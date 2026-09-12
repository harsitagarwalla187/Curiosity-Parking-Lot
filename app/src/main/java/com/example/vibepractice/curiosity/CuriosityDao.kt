package com.example.vibepractice.curiosity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CuriosityDao {

    @Query("SELECT * FROM curiosity_items ORDER BY createdAt DESC")
    fun getAllItems(): Flow<List<CuriosityItem>>

    @Query("SELECT * FROM curiosity_items WHERE status = :status ORDER BY createdAt DESC")
    fun getItemsByStatus(status: CuriosityStatus): Flow<List<CuriosityItem>>

    @Query("SELECT * FROM curiosity_items WHERE id = :id")
    suspend fun getItemById(id: Long): CuriosityItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CuriosityItem): Long

    @Update
    suspend fun updateItem(item: CuriosityItem)

    @Delete
    suspend fun deleteItem(item: CuriosityItem)

    @Query("DELETE FROM curiosity_items WHERE id = :id")
    suspend fun deleteItemById(id: Long)
}

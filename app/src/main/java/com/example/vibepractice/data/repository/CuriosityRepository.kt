package com.example.vibepractice.data.repository

import com.example.vibepractice.data.local.CuriosityDao
import com.example.vibepractice.data.model.CuriosityItem
import com.example.vibepractice.data.model.CuriosityStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

interface CuriosityRepository {
    fun getAllItems(): Flow<List<CuriosityItem>>
    fun getItemsByStatus(status: CuriosityStatus): Flow<List<CuriosityItem>>
    suspend fun insertItem(item: CuriosityItem): Long
    suspend fun updateItem(item: CuriosityItem)
    suspend fun deleteItem(item: CuriosityItem)
}

class CuriosityRepositoryImpl(
    private val dao: CuriosityDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CuriosityRepository {

    override fun getAllItems(): Flow<List<CuriosityItem>> =
        dao.getAllItems().flowOn(ioDispatcher)

    override fun getItemsByStatus(status: CuriosityStatus): Flow<List<CuriosityItem>> =
        dao.getItemsByStatus(status).flowOn(ioDispatcher)

    override suspend fun insertItem(item: CuriosityItem): Long = withContext(ioDispatcher) {
        dao.insertItem(item)
    }

    override suspend fun updateItem(item: CuriosityItem) = withContext(ioDispatcher) {
        dao.updateItem(item)
    }

    override suspend fun deleteItem(item: CuriosityItem) = withContext(ioDispatcher) {
        dao.deleteItem(item)
    }
}

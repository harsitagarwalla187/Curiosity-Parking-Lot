package com.example.vibepractice.curiosity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curiosity_items")
data class CuriosityItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val contextSnippet: String? = null,
    val source: String? = null,
    val status: CuriosityStatus = CuriosityStatus.PARKED,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

package com.example.vibepractice.core.database

import androidx.room.TypeConverter
import com.example.vibepractice.curiosity.CuriosityStatus

class Converters {
    @TypeConverter
    fun fromStatus(status: CuriosityStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(value: String): CuriosityStatus {
        return try {
            CuriosityStatus.valueOf(value)
        } catch (e: IllegalArgumentException) {
            CuriosityStatus.PARKED
        }
    }
}

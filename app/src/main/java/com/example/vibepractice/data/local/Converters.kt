package com.example.vibepractice.data.local

import androidx.room.TypeConverter
import com.example.vibepractice.data.model.CuriosityStatus

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

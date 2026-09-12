package com.example.vibepractice.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vibepractice.curiosity.CuriosityDao
import com.example.vibepractice.curiosity.CuriosityItem

@Database(
    entities = [CuriosityItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun curiosityDao(): CuriosityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "curiosity_parking_lot_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

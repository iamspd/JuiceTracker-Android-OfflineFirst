package com.example.roompractice.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roompractice.data.dao.JuiceDao
import com.example.roompractice.data.entity.JuiceEntity

@Database(entities = [JuiceEntity::class], version = 1, exportSchema = false)
abstract class JuiceDatabase : RoomDatabase() {

    // DAO instance
    abstract fun juiceDao(): JuiceDao

    // Database instance
    companion object {
        @Volatile
        private var Instance: JuiceDatabase? = null

        fun getDatabase(context: Context): JuiceDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = JuiceDatabase::class.java,
                    name = "juice_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
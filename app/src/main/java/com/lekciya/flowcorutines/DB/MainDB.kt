package com.lekciya.flowcorutines.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class MainDB : RoomDatabase() {
    abstract fun Dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDb(context: Context): MainDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "content_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
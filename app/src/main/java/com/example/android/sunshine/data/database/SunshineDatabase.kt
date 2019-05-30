package com.example.android.sunshine.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// TODO : https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we

// TODO : if i set this constructor as private as it should be in singleTone i get build error
@Database(entities = [WeatherEntry::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SunshineDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        private const val DATABASE_NAME = "weather"

        // For Singleton instantiation
        private val LOCK = Any()
        @Volatile
        private var sInstance: SunshineDatabase? = null

        fun getInstance(context: Context): SunshineDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder<SunshineDatabase>(context.applicationContext,
                                SunshineDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return sInstance
        }
    }


}

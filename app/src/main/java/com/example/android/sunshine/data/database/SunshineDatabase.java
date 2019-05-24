package com.example.android.sunshine.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

// TODO : https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we

@Database(entities = {WeatherEntry.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class SunshineDatabase extends RoomDatabase {

    // TODO : Why do synchronized needs generic object ?

    private static final String DATABASE_NAME = "weather";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile SunshineDatabase sInstance;

    // TODO : if i set this constructor as private as it should be in singleTone i get build error
    public SunshineDatabase() {
    }

    public static SunshineDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            SunshineDatabase.class, SunshineDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    public abstract WeatherDao weatherDao();


}

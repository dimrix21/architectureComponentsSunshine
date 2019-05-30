package com.example.android.sunshine.data

///*
// * Copyright (C) 2017 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.sunshine.AppExecutors
import com.example.android.sunshine.data.database.ListViewWeatherEntry
import com.example.android.sunshine.data.database.WeatherDao
import com.example.android.sunshine.data.database.WeatherEntry
import com.example.android.sunshine.data.network.WeatherNetworkDataSource
import com.example.android.sunshine.utilities.L
import com.example.android.sunshine.utilities.SunshineDateUtils
import java.util.*

///**
// * Handles data operations in Sunshine. Acts as a mediator between {@link WeatherNetworkDataSource}
// * and {@link WeatherDao}
// */
class SunshineRepository private constructor(val mWeatherDao: WeatherDao,
                                             private val mWeatherNetworkDataSource: WeatherNetworkDataSource,
                                             private val mExecutors: AppExecutors) {

    private var mInitialized = false

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
    private val isFetchNeeded: Boolean
        get() {
            val today = SunshineDateUtils.normalizedUtcDateForToday
            val count = mWeatherDao.countAllFutureWeather(today)


            L.f("fetch needed ? " + (count < WeatherNetworkDataSource.NUM_DAYS))

            return count < WeatherNetworkDataSource.NUM_DAYS
        }

    //    public LiveData<List<WeatherEntry>> getCurrentWeatherForecasts() {
    //        initializeData();
    //        Date today = SunshineDateUtils.getNormalizedUtcDateForToday() ;
    //        return mWeatherDao.getCurrentWeatherForecasts(today);
    //    }

    val futureWeather: LiveData<List<ListViewWeatherEntry>>
        get() {
            initializeData()
            return mWeatherDao.getCurrentWeatherForecasts(SunshineDateUtils.normalizedUtcDateForToday)

        }

    init {

        val networkData = mWeatherNetworkDataSource.currentWeatherForecasts
        networkData.observeForever { newForecastsFromNetwork ->
            mExecutors.diskIO().execute {
                // Deletes old historical data
                deleteOldData()
                Log.d(LOG_TAG, "Old weather deleted")
                // Insert our new weather data into Sunshine's database
                mWeatherDao.bulkInsert(*newForecastsFromNetwork)
                Log.d(LOG_TAG, "New values inserted")
            }
        }


    }

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    @Synchronized
    private fun initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return
        mInitialized = true


        // TODO : is this the place for that ? it seem like this is independent task that i would like to know exist and not
        // TODO : as part of initialize method .

        // This method call triggers Sunshine to create its task to synchronize weather data
        // periodically.
        mWeatherNetworkDataSource.scheduleRecurringFetchWeatherSync()


        mExecutors.diskIO().execute {
            if (isFetchNeeded) {
                startFetchWeatherService()
            }
        }


    }

    /**
     * Database related operations
     */

    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
    private fun deleteOldData() {
        val today = SunshineDateUtils.normalizedUtcDateForToday
        mWeatherDao.deleteOldWeather(today)
    }

    /**
     * Network related operation
     */

    private fun startFetchWeatherService() {
        mWeatherNetworkDataSource.startFetchWeatherService()
    }

    fun getWeatherByDate(date: Date): LiveData<WeatherEntry> {
        L.f("getWeatherByDate : $date")
        initializeData()
        return mWeatherDao.getWeatherByDate(date)
    }


    fun getWeatherById(i: Int): LiveData<WeatherEntry> {
        L.f("getWeatherById : $i")
        initializeData()
        return mWeatherDao.getWeatherById(i)
    }

    companion object {
        private val LOG_TAG = SunshineRepository::class.java!!.getSimpleName()

        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: SunshineRepository? = null

        @Synchronized
        fun getInstance(
                weatherDao: WeatherDao, weatherNetworkDataSource: WeatherNetworkDataSource,
                executors: AppExecutors): SunshineRepository {
            Log.d(LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = SunshineRepository(weatherDao, weatherNetworkDataSource,
                            executors)
                    Log.d(LOG_TAG, "Made new repository")
                }
            }
            return sInstance!!
        }
    }
}
package com.example.android.sunshine.utilities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.android.sunshine.AppExecutors
import com.example.android.sunshine.data.SunshineRepository

class DataBaseChecker : AppCompatActivity() {


    private var mRepository: SunshineRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mRepository = InjectorUtils.provideRepository(this)
        showDataBase()


    }


    private fun showDataBase() {
        AppExecutors.instance.diskIO().execute(Runnable {
            for (weatherEntry in mRepository!!.mWeatherDao.all) {
                L.f("Date : " + weatherEntry.date!!)
            }


            val currentWeatherForecasts = mRepository!!.mWeatherDao.getCurrentWeatherForecasts(SunshineDateUtils.normalizedUtcDateForToday)

            AppExecutors.instance.mainThread().execute(Runnable {
                currentWeatherForecasts.observe(this@DataBaseChecker, Observer { weatherEntries ->
                    if (weatherEntries == null)
                        return@Observer

                    for (weatherEntry in weatherEntries) {
                        L.f("Future Date : " + weatherEntry.date)

                    }
                })
            })


            //List<WeatherEntry> value = mRepository.mWeatherDao.getCurrentWeatherForecastsTest(SunshineDateUtils.getNormalizedUtcDateForToday());
            val value = mRepository!!.mWeatherDao.getCurrentWeatherForecasts(SunshineDateUtils.normalizedUtcDateForToday).value
                    ?: return@Runnable

            for (weatherEntry in value) {
                L.f("Future Date : " + weatherEntry.date)

            }
        })


    }
}

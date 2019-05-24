package com.example.android.sunshine.utilities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.android.sunshine.AppExecutors;
import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.ListViewWeatherEntry;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.List;

public class DataBaseChecker extends AppCompatActivity {


    private SunshineRepository mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mRepository = InjectorUtils.provideRepository(this);
        showDataBase();


    }


    private void showDataBase() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                for (WeatherEntry weatherEntry : mRepository.mWeatherDao.getAll()) {
                    L.f("Date : " + weatherEntry.getDate());
                }


                LiveData<List<ListViewWeatherEntry>> currentWeatherForecasts = mRepository.mWeatherDao.getCurrentWeatherForecasts(SunshineDateUtils.getNormalizedUtcDateForToday());

                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        currentWeatherForecasts.observe(DataBaseChecker.this, new Observer<List<ListViewWeatherEntry>>() {
                            @Override
                            public void onChanged(List<ListViewWeatherEntry> weatherEntries) {
                                if (weatherEntries == null)
                                    return;

                                for (ListViewWeatherEntry weatherEntry : weatherEntries) {
                                    L.f("Future Date : " + weatherEntry.getDate());

                                }
                            }
                        });
                    }
                });


                //List<WeatherEntry> value = mRepository.mWeatherDao.getCurrentWeatherForecastsTest(SunshineDateUtils.getNormalizedUtcDateForToday());
                List<ListViewWeatherEntry> value = mRepository.mWeatherDao.getCurrentWeatherForecasts(SunshineDateUtils.getNormalizedUtcDateForToday()).getValue();

                if (value == null)
                    return;

                for (ListViewWeatherEntry weatherEntry : value) {
                    L.f("Future Date : " + weatherEntry.getDate());

                }

            }
        });


    }
}

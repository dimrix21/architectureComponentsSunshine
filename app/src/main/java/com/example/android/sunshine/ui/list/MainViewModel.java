package com.example.android.sunshine.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.ListViewWeatherEntry;

import java.util.List;

public class MainViewModel extends ViewModel {


    LiveData<List<ListViewWeatherEntry>> mFutureWeather;
    private SunshineRepository mRepository;

    public MainViewModel(SunshineRepository repository) {

        this.mRepository = repository;
        mFutureWeather = mRepository.getFutureWeather();
    }


    public LiveData<List<ListViewWeatherEntry>> getForecast() {
        return mFutureWeather;
    }
}

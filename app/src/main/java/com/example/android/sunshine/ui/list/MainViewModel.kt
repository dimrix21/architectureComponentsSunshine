package com.example.android.sunshine.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.android.sunshine.data.SunshineRepository
import com.example.android.sunshine.data.database.ListViewWeatherEntry

class MainViewModel(private val mRepository: SunshineRepository) : ViewModel() {


    var forecast: LiveData<List<ListViewWeatherEntry>>
        internal set

    init {
        forecast = mRepository.futureWeather
    }
}

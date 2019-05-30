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

package com.example.android.sunshine.ui.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.sunshine.data.SunshineRepository
import com.example.android.sunshine.data.database.WeatherEntry
import java.util.*

/**
 * [ViewModel] for [DetailActivity]
 */
class DetailActivityViewModel(private val mRepository: SunshineRepository, // Date for the weather forecast
                              private val mDate: Date) : ViewModel() {

    // Weather forecast the user is looking at
    val weather: LiveData<WeatherEntry>


    init {
        weather = mRepository.getWeatherByDate(mDate)
    }

    companion object {

        private val TAG = "DetailActivityViewModel"
    }
}

package com.example.android.sunshine.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "weather", indices = [Index(value = "date", unique = true)])
@Entity
class ListViewWeatherEntry
/**
 * This constructor is used by OpenWeatherJsonParser. When the network fetch has JSON data, it
 * converts this data to WeatherEntry objects using this constructor.
 *
 * @param weatherIconId Image id for weather
 * @param date          Date of weather
 * @param min           Min temperature
 * @param max           Max temperature
 */

// this constructor ius used by room to set values , we added the id that was not in the original json
(@field:PrimaryKey(autoGenerate = true)
 val id: Int, val weatherIconId: Int, @PrimaryKey val date: Date, val min: Double, val max: Double)

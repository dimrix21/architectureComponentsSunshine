package com.example.android.sunshine.data.database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "weather", indices = {@Index(value = "date", unique = true)})
public class ListViewWeatherEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int weatherIconId;
    private Date date;
    private double min;
    private double max;


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
    public ListViewWeatherEntry(int id, int weatherIconId, Date date, double min, double max) {
        this.id = id;
        this.weatherIconId = weatherIconId;
        this.date = date;
        this.min = min;
        this.max = max;

    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getWeatherIconId() {
        return weatherIconId;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }


}

package com.example.android.sunshine.data.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    List<WeatherEntry> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weather);

    @Query("SELECT * FROM weather WHERE date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    @Query("SELECT * FROM weather WHERE id = :id")
    LiveData<WeatherEntry> getWeatherById(int id);


    @Delete
    void delete(WeatherEntry weather);

    @Query("DELETE FROM weather")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM weather WHERE date >= :date")
    int countAllFutureWeather(Date date);

    /**
     * Deletes any weather data older than the given day
     *
     * @param date The date to delete all prior weather from (exclusive)
     */
    @Query("DELETE FROM weather WHERE date < :date")
    void deleteOldWeather(Date date);

    @Query("SELECT * FROM weather WHERE date >= :date")
    LiveData<List<WeatherEntry>> getFutureWeather(Date date);

    /**
     * Selects all {@link WeatherEntry} entries after a give date, inclusive. The LiveData will
     * be kept in sync with the database, so that it will automatically notify observers when the
     * values in the table change.
     *
     * @param date A {@link Date} from which to select all future weather
     * @return {@link LiveData} list of all {@link WeatherEntry} objects after date
     */
    @Query("SELECT * FROM weather WHERE date >= :date")
    LiveData<List<ListViewWeatherEntry>> getCurrentWeatherForecasts(Date date);

}

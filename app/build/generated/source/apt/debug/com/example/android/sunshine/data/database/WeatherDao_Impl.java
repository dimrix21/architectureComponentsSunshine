package com.example.android.sunshine.data.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWeatherEntry;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfWeatherEntry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldWeather;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherEntry = new EntityInsertionAdapter<WeatherEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `weather`(`id`,`weatherIconId`,`date`,`min`,`max`,`humidity`,`pressure`,`wind`,`degrees`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWeatherIconId());
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        stmt.bindDouble(4, value.getMin());
        stmt.bindDouble(5, value.getMax());
        stmt.bindDouble(6, value.getHumidity());
        stmt.bindDouble(7, value.getPressure());
        stmt.bindDouble(8, value.getWind());
        stmt.bindDouble(9, value.getDegrees());
      }
    };
    this.__deletionAdapterOfWeatherEntry = new EntityDeletionOrUpdateAdapter<WeatherEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `weather` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherEntry value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM weather";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldWeather = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM weather WHERE date < ?";
        return _query;
      }
    };
  }

  @Override
  public void bulkInsert(final WeatherEntry... weather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWeatherEntry.insert(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final WeatherEntry weather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfWeatherEntry.handle(weather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void deleteOldWeather(final Date date) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldWeather.acquire();
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindLong(_argIndex, _tmp);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteOldWeather.release(_stmt);
    }
  }

  @Override
  public List<WeatherEntry> getAll() {
    final String _sql = "SELECT * FROM weather";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfWeatherIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherIconId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfMin = CursorUtil.getColumnIndexOrThrow(_cursor, "min");
      final int _cursorIndexOfMax = CursorUtil.getColumnIndexOrThrow(_cursor, "max");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
      final int _cursorIndexOfWind = CursorUtil.getColumnIndexOrThrow(_cursor, "wind");
      final int _cursorIndexOfDegrees = CursorUtil.getColumnIndexOrThrow(_cursor, "degrees");
      final List<WeatherEntry> _result = new ArrayList<WeatherEntry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final WeatherEntry _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final int _tmpWeatherIconId;
        _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        _tmpDate = DateConverter.toDate(_tmp);
        final double _tmpMin;
        _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
        final double _tmpMax;
        _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
        final double _tmpHumidity;
        _tmpHumidity = _cursor.getDouble(_cursorIndexOfHumidity);
        final double _tmpPressure;
        _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
        final double _tmpWind;
        _tmpWind = _cursor.getDouble(_cursorIndexOfWind);
        final double _tmpDegrees;
        _tmpDegrees = _cursor.getDouble(_cursorIndexOfDegrees);
        _item = new WeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax,_tmpHumidity,_tmpPressure,_tmpWind,_tmpDegrees);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<WeatherEntry> getWeatherByDate(final Date date) {
    final String _sql = "SELECT * FROM weather WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"weather"}, false, new Callable<WeatherEntry>() {
      @Override
      public WeatherEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeatherIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherIconId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMin = CursorUtil.getColumnIndexOrThrow(_cursor, "min");
          final int _cursorIndexOfMax = CursorUtil.getColumnIndexOrThrow(_cursor, "max");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
          final int _cursorIndexOfWind = CursorUtil.getColumnIndexOrThrow(_cursor, "wind");
          final int _cursorIndexOfDegrees = CursorUtil.getColumnIndexOrThrow(_cursor, "degrees");
          final WeatherEntry _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpWeatherIconId;
            _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
            final Date _tmpDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConverter.toDate(_tmp_1);
            final double _tmpMin;
            _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
            final double _tmpMax;
            _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
            final double _tmpHumidity;
            _tmpHumidity = _cursor.getDouble(_cursorIndexOfHumidity);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            final double _tmpWind;
            _tmpWind = _cursor.getDouble(_cursorIndexOfWind);
            final double _tmpDegrees;
            _tmpDegrees = _cursor.getDouble(_cursorIndexOfDegrees);
            _result = new WeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax,_tmpHumidity,_tmpPressure,_tmpWind,_tmpDegrees);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<WeatherEntry> getWeatherById(final int id) {
    final String _sql = "SELECT * FROM weather WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"weather"}, false, new Callable<WeatherEntry>() {
      @Override
      public WeatherEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeatherIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherIconId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMin = CursorUtil.getColumnIndexOrThrow(_cursor, "min");
          final int _cursorIndexOfMax = CursorUtil.getColumnIndexOrThrow(_cursor, "max");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
          final int _cursorIndexOfWind = CursorUtil.getColumnIndexOrThrow(_cursor, "wind");
          final int _cursorIndexOfDegrees = CursorUtil.getColumnIndexOrThrow(_cursor, "degrees");
          final WeatherEntry _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpWeatherIconId;
            _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConverter.toDate(_tmp);
            final double _tmpMin;
            _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
            final double _tmpMax;
            _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
            final double _tmpHumidity;
            _tmpHumidity = _cursor.getDouble(_cursorIndexOfHumidity);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            final double _tmpWind;
            _tmpWind = _cursor.getDouble(_cursorIndexOfWind);
            final double _tmpDegrees;
            _tmpDegrees = _cursor.getDouble(_cursorIndexOfDegrees);
            _result = new WeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax,_tmpHumidity,_tmpPressure,_tmpWind,_tmpDegrees);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int countAllFutureWeather(final Date date) {
    final String _sql = "SELECT COUNT(id) FROM weather WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<WeatherEntry>> getFutureWeather(final Date date) {
    final String _sql = "SELECT * FROM weather WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"weather"}, false, new Callable<List<WeatherEntry>>() {
      @Override
      public List<WeatherEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeatherIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherIconId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMin = CursorUtil.getColumnIndexOrThrow(_cursor, "min");
          final int _cursorIndexOfMax = CursorUtil.getColumnIndexOrThrow(_cursor, "max");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
          final int _cursorIndexOfWind = CursorUtil.getColumnIndexOrThrow(_cursor, "wind");
          final int _cursorIndexOfDegrees = CursorUtil.getColumnIndexOrThrow(_cursor, "degrees");
          final List<WeatherEntry> _result = new ArrayList<WeatherEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WeatherEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpWeatherIconId;
            _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
            final Date _tmpDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConverter.toDate(_tmp_1);
            final double _tmpMin;
            _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
            final double _tmpMax;
            _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
            final double _tmpHumidity;
            _tmpHumidity = _cursor.getDouble(_cursorIndexOfHumidity);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            final double _tmpWind;
            _tmpWind = _cursor.getDouble(_cursorIndexOfWind);
            final double _tmpDegrees;
            _tmpDegrees = _cursor.getDouble(_cursorIndexOfDegrees);
            _item = new WeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax,_tmpHumidity,_tmpPressure,_tmpWind,_tmpDegrees);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<ListViewWeatherEntry>> getCurrentWeatherForecasts(final Date date) {
    final String _sql = "SELECT * FROM weather WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = DateConverter.toTimestamp(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"weather"}, false, new Callable<List<ListViewWeatherEntry>>() {
      @Override
      public List<ListViewWeatherEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWeatherIconId = CursorUtil.getColumnIndexOrThrow(_cursor, "weatherIconId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMin = CursorUtil.getColumnIndexOrThrow(_cursor, "min");
          final int _cursorIndexOfMax = CursorUtil.getColumnIndexOrThrow(_cursor, "max");
          final List<ListViewWeatherEntry> _result = new ArrayList<ListViewWeatherEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ListViewWeatherEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpWeatherIconId;
            _tmpWeatherIconId = _cursor.getInt(_cursorIndexOfWeatherIconId);
            final Date _tmpDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConverter.toDate(_tmp_1);
            final double _tmpMin;
            _tmpMin = _cursor.getDouble(_cursorIndexOfMin);
            final double _tmpMax;
            _tmpMax = _cursor.getDouble(_cursorIndexOfMax);
            _item = new ListViewWeatherEntry(_tmpId,_tmpWeatherIconId,_tmpDate,_tmpMin,_tmpMax);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}

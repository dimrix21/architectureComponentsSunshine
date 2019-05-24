package com.example.android.sunshine.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class PrimaryWeatherInfoBinding extends ViewDataBinding {
  @NonNull
  public final TextView date;

  @NonNull
  public final TextView highTemperature;

  @NonNull
  public final Guideline horizontalMiddle;

  @NonNull
  public final TextView lowTemperature;

  @NonNull
  public final TextView weatherDescription;

  @NonNull
  public final ImageView weatherIcon;

  protected PrimaryWeatherInfoBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView date, TextView highTemperature, Guideline horizontalMiddle, TextView lowTemperature,
      TextView weatherDescription, ImageView weatherIcon) {
    super(_bindingComponent, _root, _localFieldCount);
    this.date = date;
    this.highTemperature = highTemperature;
    this.horizontalMiddle = horizontalMiddle;
    this.lowTemperature = lowTemperature;
    this.weatherDescription = weatherDescription;
    this.weatherIcon = weatherIcon;
  }

  @NonNull
  public static PrimaryWeatherInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.primary_weather_info, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static PrimaryWeatherInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<PrimaryWeatherInfoBinding>inflateInternal(inflater, com.example.android.sunshine.R.layout.primary_weather_info, root, attachToRoot, component);
  }

  @NonNull
  public static PrimaryWeatherInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.primary_weather_info, null, false, component)
   */
  @NonNull
  @Deprecated
  public static PrimaryWeatherInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<PrimaryWeatherInfoBinding>inflateInternal(inflater, com.example.android.sunshine.R.layout.primary_weather_info, null, false, component);
  }

  public static PrimaryWeatherInfoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static PrimaryWeatherInfoBinding bind(@NonNull View view, @Nullable Object component) {
    return (PrimaryWeatherInfoBinding)bind(component, view, com.example.android.sunshine.R.layout.primary_weather_info);
  }
}

package com.example.android.sunshine.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ExtraWeatherDetailsBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout extraDetails;

  @NonNull
  public final Guideline horizontalMiddle;

  @NonNull
  public final TextView humidity;

  @NonNull
  public final TextView humidityLabel;

  @NonNull
  public final TextView pressure;

  @NonNull
  public final TextView pressureLabel;

  @NonNull
  public final TextView windLabel;

  @NonNull
  public final TextView windMeasurement;

  protected ExtraWeatherDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout extraDetails, Guideline horizontalMiddle, TextView humidity,
      TextView humidityLabel, TextView pressure, TextView pressureLabel, TextView windLabel,
      TextView windMeasurement) {
    super(_bindingComponent, _root, _localFieldCount);
    this.extraDetails = extraDetails;
    this.horizontalMiddle = horizontalMiddle;
    this.humidity = humidity;
    this.humidityLabel = humidityLabel;
    this.pressure = pressure;
    this.pressureLabel = pressureLabel;
    this.windLabel = windLabel;
    this.windMeasurement = windMeasurement;
  }

  @NonNull
  public static ExtraWeatherDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.extra_weather_details, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ExtraWeatherDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ExtraWeatherDetailsBinding>inflateInternal(inflater, com.example.android.sunshine.R.layout.extra_weather_details, root, attachToRoot, component);
  }

  @NonNull
  public static ExtraWeatherDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.extra_weather_details, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ExtraWeatherDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ExtraWeatherDetailsBinding>inflateInternal(inflater, com.example.android.sunshine.R.layout.extra_weather_details, null, false, component);
  }

  public static ExtraWeatherDetailsBinding bind(@NonNull View view) {
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
  public static ExtraWeatherDetailsBinding bind(@NonNull View view, @Nullable Object component) {
    return (ExtraWeatherDetailsBinding)bind(component, view, com.example.android.sunshine.R.layout.extra_weather_details);
  }
}

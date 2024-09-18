package com.stmu.jqasample.core;

import java.util.function.UnaryOperator;

public interface WeatherDataCache {
    WeatherData put(WeatherData weatherData, UnaryOperator<WeatherData> onSuccess);
    WeatherData retrieve(Location location);
    void clear(Location location);
}

package com.stmu.jqasample.core;

import java.time.OffsetDateTime;

public interface WeatherDataClient {

    WeatherData getWeatherData(Location location, OffsetDateTime from, OffsetDateTime to);

}

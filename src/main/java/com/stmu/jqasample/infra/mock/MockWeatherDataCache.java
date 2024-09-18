package com.stmu.jqasample.infra.mock;

import com.stmu.jqasample.commons.DateTimeUtils;
import com.stmu.jqasample.core.Location;
import com.stmu.jqasample.core.WeatherData;
import com.stmu.jqasample.core.WeatherDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.UnaryOperator;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "env.runtime",
        havingValue = "mock",
        matchIfMissing = true
)
public class MockWeatherDataCache implements WeatherDataCache {

    @Override
    public WeatherData put(WeatherData weatherData, UnaryOperator<WeatherData> onSuccess) {
        log.info("[MOCK] caching weather data : {}", weatherData);
        return onSuccess.apply(weatherData);
    }

    @Override
    public WeatherData retrieve(Location location) {
        log.info("[MOCK] retrieving data from cache");
        return new WeatherData(location, DateTimeUtils.getLatest30MinuteInterval(), DateTimeUtils.getLatest30MinuteInterval().plusHours(24), Map.of());
    }

    @Override
    public void clear(Location location) {
        log.info("[MOCK] clear data from cache");
    }
}

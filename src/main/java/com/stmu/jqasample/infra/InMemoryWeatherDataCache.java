package com.stmu.jqasample.infra;

import com.stmu.jqasample.core.Location;
import com.stmu.jqasample.core.WeatherData;
import com.stmu.jqasample.core.WeatherDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "env.runtime",
        havingValue = "mem",
        matchIfMissing = true
)
public class InMemoryWeatherDataCache implements WeatherDataCache {
    @Override
    public WeatherData put(WeatherData weatherData, UnaryOperator<WeatherData> onSuccess) {
        return null;
    }

    @Override
    public WeatherData retrieve(Location location) {
        return null;
    }

    @Override
    public void clear(Location location) {

    }
}

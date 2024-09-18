package com.stmu.jqasample.infra.mock;

import com.stmu.jqasample.core.WeatherData;
import com.stmu.jqasample.core.WeatherDataNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "env.runtime",
        havingValue = "mock",
        matchIfMissing = true
)
public class MockWeatherDataNotifier implements WeatherDataNotifier {
    @Override
    public WeatherData notifyCreated(WeatherData weatherData) {
        log.info("[MOCK] weather data created: {}", weatherData);
        return weatherData;
    }
}

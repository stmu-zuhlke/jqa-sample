package com.stmu.jqasample.infra.mock;

import com.stmu.jqasample.core.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@ConditionalOnProperty(
        value = "env.runtime",
        havingValue = "mock",
        matchIfMissing = true
)
public class MockWeatherDataClient implements WeatherDataClient {

    @Override
    public WeatherData getWeatherData(Location location, OffsetDateTime from, OffsetDateTime to) {
        log.info("[MOCK] request weather data and actually doing nothing at all");
        return new WeatherData(location, from, to, Map.of(
                from.plusHours(1), new WeatherDetails(
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        WeatherStatus.CLOUDY),
                from.plusHours(2), new WeatherDetails(
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        WeatherStatus.CLEAR_SKY),
                from.plusHours(3), new WeatherDetails(
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        ThreadLocalRandom.current().nextDouble(-20, 50),
                        WeatherStatus.RAIN)
        ));
    }
}

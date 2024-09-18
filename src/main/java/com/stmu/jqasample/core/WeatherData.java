package com.stmu.jqasample.core;


import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Holds the weather data of a single location starting form the given from timestamp until the given to timestamp
 *
 */
@Slf4j
public record WeatherData(
        Location location,
        OffsetDateTime from,
        OffsetDateTime to,
        Map<OffsetDateTime, WeatherDetails> details) {

}

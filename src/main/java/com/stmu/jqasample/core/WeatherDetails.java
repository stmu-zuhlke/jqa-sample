package com.stmu.jqasample.core;

import lombok.Builder;

@Builder
public record WeatherDetails(
        Double temperatureInCelcius,
        Double precipitationInMm,
        Double windSpeedInMpS,
        WeatherStatus overallStatus) {
}

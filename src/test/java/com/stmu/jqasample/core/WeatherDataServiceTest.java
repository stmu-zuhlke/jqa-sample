package com.stmu.jqasample.core;

import com.stmu.jqasample.commons.DateTimeUtils;
import com.stmu.jqasample.infra.mock.MockWeatherDataCache;
import com.stmu.jqasample.infra.mock.MockWeatherDataClient;
import com.stmu.jqasample.infra.mock.MockWeatherDataNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    WeatherDataService weatherDataService;

    @Mock
    MockWeatherDataClient client;
    @Mock
    MockWeatherDataCache cache;
    @Mock
    MockWeatherDataNotifier notifier;
    @Mock
    LocationPollingRegistry registry;

    @BeforeEach
    void setUp() {
        when(client.getWeatherData(any(),any(),any())).thenCallRealMethod();
        when(cache.put(any(), any())).thenCallRealMethod();
        when(notifier.notifyCreated(any())).thenCallRealMethod();
        weatherDataService = new WeatherDataService(client,cache,notifier, registry);
    }

    @Test
    @DisplayName("Should request data for the given location")
    void weatherDataServiceLocationData() {
        Location location = new Location(2.34, 3.45);
        // WHEN
        var forecast24h = weatherDataService.requestAndCacheDataForNext24Hours(location);
        // THEN
        assertThat(forecast24h.location()).isEqualTo(location);
    }

    @Test
    @DisplayName("Should request data starting in the last 30 minutes when given a location")
    void weatherDataService30MinutesInterval() {
        // GIVEN
        var testStartInterval = DateTimeUtils.getLatest30MinuteInterval();
        Location location = new Location(2.34, 3.45);
        // WHEN
        var forecast24h = weatherDataService.requestAndCacheDataForNext24Hours(location);
        // THEN
        // need to check two intervals if the test run overlaps an interval
        var testEndInterval = DateTimeUtils.getLatest30MinuteInterval();
        assertThat(forecast24h.from()).isIn(testStartInterval, testEndInterval);
    }

    @Test
    @DisplayName("Should request weather data for the next 24 hours when given a location")
    void weatherDataService24hDataRequest() {
        // GIVEN
        Location location = new Location(2.34, 3.45);
        // WHEN
        var forecast24h = weatherDataService.requestAndCacheDataForNext24Hours(location);
        // THEN
        assertThat(forecast24h.to()).isEqualTo(forecast24h.from().plusHours(24));
    }

    @Test
    @DisplayName("Should cache data when data was requested")
    void weatherDataServiceCache() {
        // GIVEN
        Location location = new Location(2.34, 3.45);
        // WHEN
        var forecast24h = weatherDataService.requestAndCacheDataForNext24Hours(location);
        // THEN
        verify(cache, times(1)).put(argThat(arg -> arg.equals(forecast24h)), any());
        verifyNoMoreInteractions(cache);
    }

    @Test
    @DisplayName("Should send notification when data was requested")
    void weatherDataServiceNotifications() {
        // GIVEN
        Location location = new Location(2.34, 3.45);
        // WHEN
        var forecast24h = weatherDataService.requestAndCacheDataForNext24Hours(location);
        // THEN
        verify(notifier, times(1)).notifyCreated(forecast24h);
        verifyNoMoreInteractions(notifier);

    }
}
package com.stmu.jqasample.core;

import com.stmu.jqasample.commons.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class WeatherDataService {

    private WeatherDataClient dataClient;
    private WeatherDataCache cache;
    private WeatherDataNotifier notifier;
    private LocationPollingRegistry pollingRegistry;

    public WeatherData requestAndCacheDataForNext24Hours(Location location) {
        var last30Minutes = DateTimeUtils.getLatest30MinuteInterval();
        var in24Hours = last30Minutes.plusHours(24);
        var data = dataClient.getWeatherData(location, last30Minutes, in24Hours);
        return cache.put(data, notifier::notifyCreated);
    }

    public WeatherData getCachedWeatherForLocation(Location location) {
        return cache.retrieve(location);
    }

    public void clearCachedWeatherForLocation(Location location) {
        cache.clear(location);
    }

    public void registerLocation(Location location) {
        pollingRegistry.register(location);
    }

    public void pollAllLocations() {
        pollingRegistry.getAll().forEach(this::requestAndCacheDataForNext24Hours);
    }



}

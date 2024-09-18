package com.stmu.jqasample.api;

import com.stmu.jqasample.core.Location;
import com.stmu.jqasample.core.WeatherData;
import com.stmu.jqasample.core.WeatherDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController()
@RequestMapping("/api/v1")
public class ApiController {

    private final WeatherDataService weatherDataService;

    @GetMapping("weather/location/{lat}/{lon}")
    public WeatherData getHashedWeatherForLocation(@PathVariable Double lat, @PathVariable Double lon) {
        var loc = new Location(lat, lon);
        return weatherDataService.getCachedWeatherForLocation(loc);
    }

    @PostMapping("weather/24h")
    public WeatherData requestWeather(@RequestBody Location location) {
        return weatherDataService.requestAndCacheDataForNext24Hours(location);
    }

    @DeleteMapping("weather/location/{lat}/{lon}")
     public void deleteFromCache(@PathVariable Double lat, @PathVariable Double lon) {
        var loc = new Location(lat, lon);
        weatherDataService.clearCachedWeatherForLocation(loc);
    }


}

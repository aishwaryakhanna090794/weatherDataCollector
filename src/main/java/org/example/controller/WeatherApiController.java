package org.example.controller;


import org.example.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
public class WeatherApiController {

    @Autowired
    private final WeatherDataService weatherDataService;

    public WeatherApiController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/get/{cityId}/{appId}")
    public ResponseEntity<String> getWeatherForecastByCityIdAndAppID(@PathVariable("cityId") String cityId,
                                                                     @PathVariable("appId") String appId) throws Exception {

        if (cityId != null && appId != null) {

            try {

                weatherDataService.getWeatherForecastByCityIdAndAppID(cityId, appId);
                return new ResponseEntity<>("Data successfully inserted", HttpStatus.OK);
            } catch (Exception e) {
                System.out.print("Something went wrong" +e);
            }
        }
        return null;
    }
}

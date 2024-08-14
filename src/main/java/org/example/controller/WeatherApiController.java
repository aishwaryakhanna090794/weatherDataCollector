package org.example.controller;


import org.example.service.WeatherDataService;
import org.json.JSONObject;
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
                System.out.print("Something went wrong :: See Error Trace -->" + e.getMessage() +"Error Finshed");
                return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid Parameters", HttpStatus.BAD_REQUEST);
        }
    }
}

package org.example.models;

import org.springframework.stereotype.Component;

@Component
public class WeatherData {

    private String city;
    private String temp;
    private String id;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package org.example.service;


import org.example.models.WeatherData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherDataService {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Autowired
    WeatherData weatherData;
    private static final String TABLE_NAME = "WeatherData";

    public void getWeatherForecastByCityIdAndAppID(String cityId, String appid) throws Exception {

        String websiteRequest = "http://api.openweathermap.org/data/2.5/weather?q=" + cityId + "&appid=" + appid;

        System.out.println(websiteRequest);
        RestTemplate restTemplate = new RestTemplate();
        String websiteResponse = restTemplate.getForObject(websiteRequest, String.class);

        System.out.println(websiteResponse);

        JSONObject root = new JSONObject(websiteResponse);
        int responseCode = root.getInt("cod");
        if (responseCode == 200) {
            JSONObject main = root.getJSONObject("main");
            if (!main.isEmpty()) {
                weatherData.setTemp(String.valueOf(main.getFloat("temp")));
                weatherData.setId(String.valueOf((int)(Math.random() *(1000-1))));
                weatherData.setCity(cityId);

                insertWeatherData(weatherData);
            }
        }
    }


    public void insertWeatherData(WeatherData weatherData) throws Exception {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("Id", AttributeValue.builder().s(weatherData.getId()).build());
        item.put("city", AttributeValue.builder().s(weatherData.getCity()).build());
        item.put("temperature", AttributeValue.builder().s(weatherData.getTemp()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }
}







package org.example.adapterapp.adapter.service;


import org.example.adapterapp.adapter.dto.Coordinates;
import org.example.adapterapp.adapter.dto.MessageServiceA;
import org.example.adapterapp.adapter.dto.MessageServiceB;
import org.example.adapterapp.adapter.dto.WeatherNinjaResponse;
import org.example.adapterapp.adapter.exception.NotConnectionWeatherApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

/**
 * Сервисный класс осуществляющий получение и обработку данных с погодного сервиса
 */
@Service
public class AdapterService {
    /**
     * Компонент для выполнения HTTP запросов. Используется для общения с внешними веб-сервисами.
     */
    private final RestTemplate restTemplate;

    @Autowired
    public AdapterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /**
     * Адрес погодного сервиса
     */
    @Value("${weather.url}")
    private String weatherUrl;
    /**
     * Header Key
     */
    @Value("${weather.headerKey}")
    private String headerKey;
    /**
     * Header Token Api
     */
    @Value("${weather.headerToken}")
    private String headerValue;


    /**
     * Метод для получения объекта WeatherNinjaResponse из погодного сервиса по указанным координатам
     * @param coordinates - координаты (долгота, широта)
     * @return WeatherNinjaResponse
     */
    public WeatherNinjaResponse getWeather (Coordinates coordinates){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude());

        HttpHeaders headers = new HttpHeaders();
        headers.set(headerKey, headerValue);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<WeatherNinjaResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherNinjaResponse.class);

        return  response.getBody();
    }

    /**
     * Метод для конвертации объекта MessageServiceA в объект public class MessageServiceB
     * содержит запрос в погодный сервис
     * @param msgA - входящий объект из сервиса A
     * @return msgB
     */
    public MessageServiceB convertToMsgB(MessageServiceA msgA) throws NotConnectionWeatherApiException {
        MessageServiceB msgB = new MessageServiceB();
        WeatherNinjaResponse weatherNinjaResponse = getWeather(msgA.getCoordinates());
        msgB.setTxt(msgA.getMsg());
        msgB.setCreatedDt(LocalDateTime.now());
        msgB.setCurrentTemp(weatherNinjaResponse.getTemperature());
        return msgB;
    }



}

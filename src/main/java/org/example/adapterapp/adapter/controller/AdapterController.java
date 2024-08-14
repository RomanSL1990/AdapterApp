package org.example.adapterapp.adapter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.adapterapp.adapter.dto.Language;
import org.example.adapterapp.adapter.dto.MessageServiceA;
import org.example.adapterapp.adapter.dto.MessageServiceB;
import org.example.adapterapp.adapter.exception.NotConnectionWeatherApiException;
import org.example.adapterapp.adapter.exception.NotFoundMessageServicesException;
import org.example.adapterapp.adapter.service.AdapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * Класс controller служайщий для отправки данных в сервис B
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Контроллер служащий для отправки данных в сервис B",
        description = "Отправка данных в сервис B")

public class AdapterController {

    private final AdapterService adapterService;

    /**
     * Url сервиса B
     */
    @Value("${emul.serviceB.Url}")
    private String serviceBUrl;

    @Autowired
    public AdapterController(AdapterService adapterService) {
        this.adapterService = adapterService;
    }

    /**
     * Метод контроллера для обработки сообщений и отправки объекта MessageServiceB в сервис B
     * содержит вызов метода проверки на коректность сообщения поступаемого из сервиса A
     * содержит отправку сообщения в сервис B
     *
     * @param msgA - входящий объект из сервиса A
     * @return статус отправки сообщения или объект содержащий ошибку невалидных данных
     */
    @Operation(summary = "Получение данные из сервиса A,\n" +
            " обогащает их из погодного сервиса и отправляет в сервис B",
            description = "Метод принимает объект из сервиса A. Возвращает статус отправки сообщения или объект содержащий ошибку невалидных данных.")
    @PostMapping("/processWeather")
    public ResponseEntity<?> getWeatherData(@RequestBody MessageServiceA msgA) {
        if (msgA == null || msgA.isIncorrect()) {
            throw new NotFoundMessageServicesException("Сообщение отсутствует или некорректно!");
        }

        if (msgA.getLng() != Language.RU) {
            // Игнорирование сообщений с lng отличным от RU.
            return ResponseEntity.badRequest().body("Неверное или отсутствующее значение языка.");
        }
        MessageServiceB msgB = null;
        try {
            msgB = adapterService.convertToMsgB(msgA);
        } catch (ResourceAccessException e) {
            if (e.getRootCause() instanceof UnknownHostException) {
                throw new NotConnectionWeatherApiException("Сервис погоды недоступен или неверный адрес");
            }

        }
        sendToServiceB(msgB);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    /**
     * Метод производит отправку сообщения в сервис B
     *
     * @param msgB - объект для отправки в сервис B
     * @return отправляет msgB в сервис B
     */
    private ResponseEntity<MessageServiceB> sendToServiceB(MessageServiceB msgB) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(serviceBUrl, msgB, MessageServiceB.class);
    }

}

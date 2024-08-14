package org.example.adapterapp.adapter.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Ответ, представляющий ошибку связи с внешним сервисом погоды.
 * Ответ содержит сообщение об ошибке и временную метку.
 */
@AllArgsConstructor
@Getter
@Setter
public class NotConnectionWeatherResponse {
    private String message;
    private Long timestamp;

}

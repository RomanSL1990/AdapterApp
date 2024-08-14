package org.example.adapterapp.adapter.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Ответ, представляющий ошибку отсутствия ожидаемого сообщения от сервиса.
 * Ответ содержит сообщение об ошибке и временную метку.
 */
@AllArgsConstructor
@Getter
@Setter
public class NotFoundMessageServicesResponse {
    private String message;
    private Long timestamp;
}

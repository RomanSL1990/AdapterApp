package org.example.adapterapp.adapter.exception;

/**
 * Исключение для ситуаций, когда не удается установить соединение с внешним API погоды.
 */
public class NotConnectionWeatherApiException extends RuntimeException {
    public NotConnectionWeatherApiException(String message) {
        super(message);
    }
}
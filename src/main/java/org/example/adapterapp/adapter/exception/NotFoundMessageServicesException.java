package org.example.adapterapp.adapter.exception;
/**
 * Исключение для ситуаций, когда сообщение от сервиса отсутствует или не соответствует ожидаемому формату.
 */
public class NotFoundMessageServicesException extends RuntimeException{
    public NotFoundMessageServicesException(String message) {
        super(message);
    }
}

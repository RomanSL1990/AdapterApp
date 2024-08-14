package org.example.adapterapp.adapter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс - DTO, для хранения сообщений, отправляемых в сервис B
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageServiceB {
    /**
     * Текст сообщения
     */
    private String txt;
    /**
     * Дата формирования сообщения, согласно патерну
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdDt;
    /**
     * Текущая температура
     */
    private Integer currentTemp;
}

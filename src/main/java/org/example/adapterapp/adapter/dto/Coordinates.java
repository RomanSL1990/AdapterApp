package org.example.adapterapp.adapter.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


/**
 * Класс для хранения координат
 */
public class Coordinates {
    /**
     * Координаты широты
     */
    private String latitude;
    /**
     * Координаты долготы
     */
    private String longitude;

}

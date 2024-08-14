package org.example.adapterapp.adapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;


/**
 * Перечисление языкового типа сообщения
 */
public enum Language {
    EN,
    RU,
    ES;

    /**
     * Преобразует все входящие данные в верхний регистр
     * @param value - языковой параметр
     * @return преобразует параметр в верхний регистр
     */
    @JsonCreator // Десериализация строк в нижнем регистре в значения enum
    public static Language fromValue(String value) {
        try {
            return Language.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Возвращаем null или выбрасываем кастомное исключение, если значение не найдено
            return null; // Или throw new CustomEnumValidationException("Неверное значение для Language: " + value);
        }
    }
}




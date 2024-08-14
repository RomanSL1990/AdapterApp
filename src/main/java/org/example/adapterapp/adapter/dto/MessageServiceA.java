package org.example.adapterapp.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Класс - DTO, для хранения сообщений, отправляемых сервисом A
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageServiceA {

        /**
         * Текст сообщения
         */
        private String msg;
        /**
         * Язык
         */
        private Language lng;
        /**
         * Координаты
         */
        private  Coordinates coordinates;

        /**
         * Проверяет корректность сообщения.
         * Сообщение считается некорректным, если оно пусто или содержит только пробельные символы.
         * @return true, если сообщение некорректно, иначе false.
         */
        public boolean isIncorrect() {
            return msg == null || msg.isBlank();
        }

    }


package org.example.adapterapp.adapter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * Конфигурационный класс Spring для создания и настройки RestTemplate
  * Этот конфигурационный класс определяет бин RestTemplate, который можно автоматически внедрять в другие компоненты приложения.
 */
@Configuration
public class RestTemplateConfig {
    /**
     * Создаёт бин и возвращает экземпляр RestTemplate, готовый к использованию в приложении.
     */

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

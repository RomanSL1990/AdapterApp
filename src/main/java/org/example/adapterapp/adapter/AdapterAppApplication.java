package org.example.adapterapp.adapter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "AdapterApp",
                description = """
                          Микросервис 'AdapterApp' служит для обработки данных между сервисом А и сервисом B,
                          обогащая их информацией о погоде от внешнего API.
                          Приложение принимает данные от сервиса А, фильтрует их по обрабатывает сообщения только с признаком "lng": "ru",
                          затем запрашивает информацию о погоде исходя из координат.
                          Полученными данными формирует и обогащает новый объект для передачи в сервис B, включая в него данные о текущей погоде.
                          Это позволяет сервису B получать расширенную информацию, необходимую для выполнения своих функций.
                        """,

                version = "1.0"
                )
)

public class AdapterAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdapterAppApplication.class, args);
    }

}

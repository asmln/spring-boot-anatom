package sag.example.spring_boot_anatom.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegistrator {
    @Bean
    BornToBeDestroyed createBean() {
        IO.println("\uD83E\uDDEA[@Bean destroy] Создание бина bornToBeDestroyed через @Bean.");
        return new BornToBeDestroyed();
    }
}

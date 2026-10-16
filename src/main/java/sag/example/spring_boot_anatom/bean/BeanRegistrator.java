package sag.example.spring_boot_anatom.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

@Configuration
public class BeanRegistrator {
    @Bean
    BornToBeDestroyed createBean() {
        Anatom.out(Slice.BEAN, "\uD83E\uDDEA[@Bean destroy] Создание бина bornToBeDestroyed через @Bean.");
        return new BornToBeDestroyed();
    }
}

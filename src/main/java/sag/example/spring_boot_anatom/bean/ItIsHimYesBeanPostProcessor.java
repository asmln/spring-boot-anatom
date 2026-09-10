package sag.example.spring_boot_anatom.bean;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ItIsHimYesBeanPostProcessor implements BeanPostProcessor, Ordered {

    public ItIsHimYesBeanPostProcessor() {
        IO.println("---\uD83D\uDC49 Создание кастомного BeanPostProcessor. Ему можно задать порядок срабатывания в цепочке BeanPostProcessor'ов через имплементацию интерфейса Ordered.");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        // Проверяем, помечен ли класс нашей аннотацией
        if (bean.getClass().isAnnotationPresent(LogLifecycle.class)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1 [BPP postProcessBeforeInitialization] Инициализация бина (%s) запущена.%n", beanName);
            System.out.println("[BPP postProcessBeforeInitialization] Тут можно сделать что-то перед инициализацией бина. Например сохранить бины определённого класса, пока они не прокси, для дальнейшей обработки.");
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogLifecycle.class)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1 [BPP postProcessAfterInitialization] Инициализация бина (%s) завершена.%n", beanName);
            System.out.println("Тут можно в свой прокси завернуть.");
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

    @Override
    public int getOrder() {
        IO.println("---\uD83D\uDE31 [BPP Order::getOrder] Оу, кто-то потрогал мой порядок.");
        return 0;
    }
}

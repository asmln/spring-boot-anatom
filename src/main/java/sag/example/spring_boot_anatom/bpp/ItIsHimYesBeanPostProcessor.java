package sag.example.spring_boot_anatom.bpp;

import org.jspecify.annotations.NonNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.bean.LogLifecycle;

@Component
public class ItIsHimYesBeanPostProcessor implements BeanPostProcessor {

    public ItIsHimYesBeanPostProcessor() {
        IO.println("""
                ---🏗️[BPP] Создание кастомного BeanPostProcessor.
                   Ему можно задать порядок срабатывания в цепочке BeanPostProcessor'ов через имплементацию интерфейса Ordered.
                   \uD83E\uDDE0Но с порядком BPP всё довольно сложно, у этого BPP не задаю порядок, чтобы его postProcessAfterInitialization после проксирующих BPP выполнился."""
        );
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        // Проверяем, помечен ли класс нашей аннотацией
        if (bean.getClass().isAnnotationPresent(LogLifecycle.class)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1[BPP postProcessBeforeInitialization] Инициализация бина (%s) запущена.%n", beanName);
            IO.println("     ✅[BPP postProcessBeforeInitialization] Тут можно сделать что-то перед инициализацией бина.");
            IO.println("     Например сохранить класс бина, пока он не прокси, а в postProcessAfterInitialization по этому классу (через AopUtils.getTargetClass(bean)) поймать нужный бин.");
            IO.println("     Или можно попробовать настроить порядок нашего BPP и postProcessAfterInitialization выполнится до проксирования.");
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        // Проверяем имя, т.к. тут наш многострадальный бин уже запроксирован и аннотацию мы не найдём.
        // Можно на класс AopUtils.getTargetClass(bean) ориентироваться.
        if ("beanForHisLifeCycleDissection".equals(beanName)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1[BPP postProcessAfterInitialization] Инициализация бина (%s) завершена.%n", beanName);
            IO.println("     ✅[BPP postProcessAfterInitialization] Тут можно в свой прокси завернуть.");

            // Проверяем, завернул ли Spring наш многострадальный бин в прокси
            if (AopUtils.isAopProxy(bean)) {
                String proxyType = AopUtils.isCglibProxy(bean) ? "CGLIB" : "JDK Dynamic Proxy";
                // Получаем класс оригинального объекта (target)
                Class<?> targetClass = AopUtils.getTargetClass(bean);
                System.out.printf(
                        "-----\uD83E\uDED8\uD83D\uDD04[BPP Proxy Detected] Бин %s (оригинальный класс: %s) обернут в %s прокси.%n",
                        beanName,
                        targetClass.getName(),
                        proxyType
                );
            }
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

//    @Override
//    public int getOrder() {
//        IO.println("-----\uD83D\uDE31 [BPP Order::getOrder] Оу, кто-то потрогал мой order.");
//        return Ordered.LOWEST_PRECEDENCE;
//    }
}

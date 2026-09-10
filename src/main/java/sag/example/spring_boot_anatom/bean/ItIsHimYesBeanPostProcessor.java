package sag.example.spring_boot_anatom.bean;

import org.jspecify.annotations.NonNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ItIsHimYesBeanPostProcessor implements
        BeanPostProcessor, // Как правило, этого достаточно для своего BPP.
        MergedBeanDefinitionPostProcessor, // Это костыль, т.к. я очень хочу, чтобы этот BPP оказался в списке после проксирующих BPP.
        Ordered
{

    public ItIsHimYesBeanPostProcessor() {
        IO.println("""
                ---\uD83D\uDC49 [BPP] Создание кастомного BeanPostProcessor.
                Ему можно задать порядок срабатывания в цепочке BeanPostProcessor'ов через имплементацию интерфейса Ordered.
                \uD83E\uDDE0 Но с порядком BPP всё довольно сложно."""
        );
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        // Проверяем, помечен ли класс нашей аннотацией
        if (bean.getClass().isAnnotationPresent(LogLifecycle.class)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1 [BPP postProcessBeforeInitialization] Инициализация бина (%s) запущена.%n", beanName);
            IO.println("""
                    ✅[BPP postProcessBeforeInitialization] Тут можно сделать что-то перед инициализацией бина.
                    Например сохранить класс бина, пока он не прокси, а в postProcessAfterInitialization по этому классу (через AopUtils.getTargetClass(bean)) поймать нужный бин.
                    Или можно попробовать настроить порядок нашего BPP и postProcessAfterInitialization выполнится до проксирования.""");
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        // Проверяем имя, т.к. тут наш многострадальный бин уже запроксирован и аннотацию мы не найдём.
        // Можно на класс AopUtils.getTargetClass(bean) ориентироваться.
        if ("beanForHisLifeCycleDissection".equals(beanName)) {
            System.out.printf("-----\uD83E\uDED8\uD83D\uDCA1 [BPP postProcessAfterInitialization] Инициализация бина (%s) завершена.%n", beanName);
            IO.println("✅[BPP postProcessAfterInitialization] Тут можно в свой прокси завернуть.");

            // Проверяем, завернул ли Spring наш многострадальный бин в прокси
            if (AopUtils.isAopProxy(bean)) {
                String proxyType = AopUtils.isCglibProxy(bean) ? "CGLIB" : "JDK Dynamic Proxy";
                // Получаем класс оригинального объекта (target)
                Class<?> targetClass = AopUtils.getTargetClass(bean);
                System.out.printf(
                        "-----\uD83E\uDED8\uD83D\uDD04 [BPP Proxy Detected] Бин %s (оригинальный класс: %s) обернут в %s прокси.%n",
                        beanName,
                        targetClass.getName(),
                        proxyType
                );
            }
        }
        return bean; // Обязательно возвращаем объект (тот же или измененный)
    }

    @Override
    public void postProcessMergedBeanDefinition(@NonNull RootBeanDefinition beanDefinition, @NonNull Class<?> beanType, @NonNull String beanName) {

    }

    @Override
    public void resetBeanDefinition(@NonNull String beanName) {
        MergedBeanDefinitionPostProcessor.super.resetBeanDefinition(beanName);
    }

    @Override
    public int getOrder() {
        IO.println("-----\uD83D\uDE31 [BPP Order::getOrder] Оу, кто-то потрогал мой порядок.");
        return Ordered.LOWEST_PRECEDENCE;
    }
}

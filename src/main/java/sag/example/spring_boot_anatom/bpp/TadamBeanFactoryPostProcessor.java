package sag.example.spring_boot_anatom.bpp;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TadamBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public TadamBeanFactoryPostProcessor() {
        IO.println("---\uD83C\uDFED[BFPP] Создание кастомного BeanFactoryPostProcessor.");
        IO.println("   ✅[BFPP] Штука, которая работает с BeanDefinition (описаниями бинов).");
        IO.println("   [BFPP] BeanFactoryPostProcessor должен взаимодействовать только с объектами BeanDefinition. Внутри него категорически нельзя ❌ запрашивать готовые бины из контекста.");
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanForHisLifeCycleDissection");
        beanDefinition.setDescription("Убийца - дворецкий!");
    }
}

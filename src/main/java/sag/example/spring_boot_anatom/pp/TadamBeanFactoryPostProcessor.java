package sag.example.spring_boot_anatom.pp;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

@Component
public class TadamBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public TadamBeanFactoryPostProcessor() {
        Anatom.out(Slice.SPRING,"---\uD83C\uDFED[BFPP] Создание кастомного BeanFactoryPostProcessor.");
        Anatom.out(Slice.SPRING,"   ✅[BFPP] Штука, которая работает с BeanDefinition (описаниями бинов).");
        Anatom.out(Slice.SPRING,"   [BFPP] BeanFactoryPostProcessor должен взаимодействовать только с объектами BeanDefinition. Внутри него категорически нельзя ❌ запрашивать готовые бины из контекста.");
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanForHisLifeCycleDissection");
        beanDefinition.setDescription("Убийца - дворецкий!");
    }
}

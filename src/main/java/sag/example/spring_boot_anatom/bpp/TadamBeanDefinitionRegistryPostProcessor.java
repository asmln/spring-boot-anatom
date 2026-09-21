package sag.example.spring_boot_anatom.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TadamBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    public TadamBeanDefinitionRegistryPostProcessor() {
        IO.println("---\uD83C\uDFED[BDRPP (BFPP)] Создание кастомного BeanDefinitionRegistryPostProcessor (это реализация [BFPP]).");
        IO.println("   ✅[BDRPP (BFPP)] Штука, которая работает с BeanDefinition и может регистрировать новые BeanDefinition в методе postProcessBeanDefinitionRegistry.");
        IO.println("   [BDRPP (BFPP)] BeanDefinitionRegistryPostProcessor должен взаимодействовать только с объектами BeanDefinition. Внутри него категорически нельзя ❌ запрашивать готовые бины из контекста.");
        IO.println("   ✅[BDRPP (BFPP)] Хорошее место для логирования BeanDefinition бинов, которые в этом приложении создаются (в методе postProcessBeanDefinitionRegistry).");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinition beanDefinition = registry.getBeanDefinition("beanForHisLifeCycleDissection");
        IO.println(
                "-----\uD83E\uDED8\uD83D\uDCA1[Bean Life Cycle] Сформирован BeanDefinition нашего препарируемого бедолаги: beanForHisLifeCycleDissection (%s)."
                    .formatted(beanDefinition.getBeanClassName())
        );
    }
}

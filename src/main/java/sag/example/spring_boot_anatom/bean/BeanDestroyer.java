package sag.example.spring_boot_anatom.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

// Для реализации уничтожения бина
@Service
public class BeanDestroyer {
    private final ApplicationContext context;
    private final Bean bean;

    public BeanDestroyer(
            ApplicationContext context,
            @Qualifier("qualifierForBeanForHisLifeCycleDissection") Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    public void killBean() {
        // Получаем доступ к внутренней фабрике бинов
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        // Проверяем, существует ли синглтон в реестре
        if (beanFactory.containsSingleton(bean.getBeanName())) {
            // Уничтожаем инстанс бина
            beanFactory.destroySingleton(bean.getBeanName());
            // Полностью удаляем описание бина (BeanDefinition)
            beanFactory.removeBeanDefinition(bean.getBeanName());
            System.out.printf("-----\uD83D\uDCA5 Бин %s успешно уничтожен и имя его забыто.%n", bean.getBeanName());
        } else {
            System.out.printf("-----\uD83E\uDDD0 Бин %s не найден.%n", bean.getBeanName());
        }
    }
}

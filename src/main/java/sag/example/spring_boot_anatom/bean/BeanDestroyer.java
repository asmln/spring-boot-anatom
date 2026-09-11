package sag.example.spring_boot_anatom.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

// Для реализации уничтожения бина
@Service
@Description("Дворецкий")
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
        String beanName = bean.getBeanName();
        // Получаем доступ к внутренней фабрике бинов
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        // Проверяем, существует ли синглтон в реестре
        if (beanFactory.containsSingleton(beanName)) {
            bean.theFinalWord(
                    beanFactory.getBeanDefinition(beanName).getDescription()
            );
            // Уничтожаем инстанс бина
            beanFactory.destroySingleton(beanName);
            // Полностью удаляем описание бина (BeanDefinition)
            //beanFactory.removeBeanDefinition(beanName); // Из-за этого иногда вываливается исключение во время дальнейшего закрытия контекста
            System.out.printf("-----\uD83D\uDCA5Бин %s успешно уничтожен.%n", beanName);
        } else {
            System.out.printf("-----\uD83E\uDDD0Бин %s не найден.%n", beanName);
        }
    }
}

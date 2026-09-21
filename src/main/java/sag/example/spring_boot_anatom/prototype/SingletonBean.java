package sag.example.spring_boot_anatom.prototype;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SingletonBean {

    private final PrototypeBean noPrototypeBean; // ❗ Так у нас будет только один конкретный экземпляр прототипа для этого синглтона.
    private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
    private final ApplicationContext context; // ❗ Это из пушки по воробьям - не надо инжектить контекст в бин.

    public SingletonBean(
            PrototypeBean noPrototypeBean,
            ObjectProvider<PrototypeBean> prototypeBeanProvider,
            ApplicationContext context
    ) {
        this.noPrototypeBean = noPrototypeBean;
        this.prototypeBeanProvider = prototypeBeanProvider;
        this.context = context;
    }

    @Lookup
    // ❗Метод не должен быть private
    PrototypeBean getNewPrototypeBean() {
        IO.println("\uD83E\uDE84[Prototype Injection] Этот код не вызовется. Spring переопределит метод для выдачи прототипов.");
        return null;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void showMeYourPrototypes() throws InterruptedException {
        IO.println("===== \uD83E\uDE84[Prototype Injection]");
        IO.println(
                "   \uD83E\uDE84[Prototype Injection] Синглтоновый Прототип - один экземпляр прототипа на всё время жизни синглтона-хозяина: %s."
                        .formatted(noPrototypeBean)
        );
        Stream.of(1,2).forEach(_ -> IO.println(
                "   \uD83E\uDE84[Prototype Injection] Каждый раз новый прототип через context (но так лучше не делать): %s."
                        .formatted(context.getBean(PrototypeBean.class))
        ));
        Stream.of(1,2).forEach(_ -> IO.println(
                "   \uD83E\uDE84[Prototype Injection] Каждый раз новый прототип через @Lookup: %s."
                        .formatted(getNewPrototypeBean())
        ));
        Stream.of(1,2).forEach(_ -> IO.println(
                "   \uD83E\uDE84✅[Prototype Injection] Каждый раз новый прототип через ObjectProvider: %s."
                        .formatted(prototypeBeanProvider.getObject())
        ));
        IO.println("=====\n");
    }
}

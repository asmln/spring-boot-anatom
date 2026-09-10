package sag.example.spring_boot_anatom.events;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.bean.BeanDestroyer;

@Component
public class AsyncApplicationReadyEventListener {

    private final BeanDestroyer beanDestroyer;

    public AsyncApplicationReadyEventListener(BeanDestroyer beanDestroyer) {
        IO.println("---\uD83D\uDC49 Создание Listener'а для подписки на события Spring-Boot приложения.");
        this.beanDestroyer = beanDestroyer;
    }

    @Async // Требуется @EnableAsync в конфигурации приложения
    @EventListener(ApplicationReadyEvent.class)
    public void appReady() {
        IO.println("---\uD83D\uDC49 ApplicationReadyEvent - старт приложения завершён! \uD83C\uDF89");
        IO.println("✅ Хорошее место для прогрева кэшей.");
        beanDestroyer.killBean();
    }
}

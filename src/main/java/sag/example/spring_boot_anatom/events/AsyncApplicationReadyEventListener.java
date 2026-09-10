package sag.example.spring_boot_anatom.events;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncApplicationReadyEventListener {
    @Async // Требуется @EnableAsync в конфигурации приложения
    @EventListener(ApplicationReadyEvent.class)
    public void appReady() {
        IO.println("--- ApplicationReadyEvent.");
        IO.println("Хорошее место для прогрева кэшей.");
    }
}

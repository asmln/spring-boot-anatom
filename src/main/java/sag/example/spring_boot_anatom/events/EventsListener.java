package sag.example.spring_boot_anatom.events;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.bean.BeanDestroyer;

@Component
public class EventsListener {

    private final BeanDestroyer beanDestroyer;

    public EventsListener(BeanDestroyer beanDestroyer) {
        IO.println("---\uD83D\uDC42Создание Listener'а для подписки на события Spring-Boot приложения.");
        this.beanDestroyer = beanDestroyer;
    }

    @EventListener(ApplicationStartingEvent.class)
    public void appStarting() {
        IO.println("Этот текст вы не увидите - отгадайте, почему.");
    }

    @EventListener(ApplicationEnvironmentPreparedEvent.class)
    public void appEnvironmentPrepared() {
        IO.println("Этот текст вы не увидите - отгадайте, почему.");
    }

    @Async // Требуется @EnableAsync в конфигурации приложения
    @EventListener(ApplicationReadyEvent.class)
    public void appReady() {
        IO.println("---\uD83D\uDC13ApplicationReadyEvent - старт приложения завершён! \uD83C\uDF89");
        IO.println("   ✅Хорошее место для прогрева кэшей.");
        beanDestroyer.killBean();
    }

//    Если этот обработчик здесь добавить, тогда почему-то Spring пытается заинжектить Bean, который уже убит.
//    @EventListener(ContextClosedEvent.class)
//    public void appContextClosedEvent() {
//        IO.println("---\uD83D\uDC13\uD83D\uDD2AContextClosedEvent - контекст закрывается. Бины еще живы, но процесс уничтожения начался.");
//        IO.println("   ✅Можно прологировать завершение работы, очистить ресурсы.");
//    }
}

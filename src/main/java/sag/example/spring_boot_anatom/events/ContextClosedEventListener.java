package sag.example.spring_boot_anatom.events;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedEventListener {
    @EventListener(ContextClosedEvent.class)
    public void appContextClosedEvent() {
        IO.println("---\uD83D\uDC13\uD83D\uDD2AContextClosedEvent - контекст закрывается. Бины еще живы, но процесс уничтожения начался.");
        IO.println("   ✅Можно прологировать завершение работы, очистить ресурсы.");
    }
}

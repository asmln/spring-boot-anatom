package sag.example.spring_boot_anatom.events;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

import java.util.List;

@Component
public class ContextClosedEventListener {
    @EventListener(ContextClosedEvent.class)
    public void appContextClosedEvent() {
        Anatom.out(List.of(Slice.SPRING, Slice.EVENT), "---\uD83D\uDC13\uD83D\uDD2AContextClosedEvent - контекст закрывается. Бины еще живы, но процесс уничтожения начался.");
        Anatom.out(List.of(Slice.SPRING, Slice.EVENT), "   ✅Можно прологировать завершение работы, очистить ресурсы.");
    }
}

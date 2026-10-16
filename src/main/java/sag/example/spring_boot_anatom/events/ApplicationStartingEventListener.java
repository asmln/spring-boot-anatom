package sag.example.spring_boot_anatom.events;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

import java.util.List;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(@NonNull ApplicationStartingEvent event) {
        Anatom.out(List.of(Slice.SPRING, Slice.EVENT), "---\uD83D\uDC23ApplicationStartingEvent - в самом начале запуска, контекст ещё не создан.");
    }
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

package sag.example.spring_boot_anatom.events;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(@NonNull ApplicationStartingEvent event) {
        IO.println("---\uD83D\uDC23ApplicationStartingEvent - в самом начале запуска, контекст ещё не создан.");
    }
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

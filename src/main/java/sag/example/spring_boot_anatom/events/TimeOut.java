package sag.example.spring_boot_anatom.events;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TimeOut {
    @EventListener(ApplicationReadyEvent.class)
    public void appReady() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
}

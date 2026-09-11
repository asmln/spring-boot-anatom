package sag.example.spring_boot_anatom.prop;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@PropertySource(value="classpath:custom.properties", encoding = "UTF-8")
public class Court {

    private final CatchTheThief catchTheThief;

    public Court(CatchTheThief catchTheThief) {
        this.catchTheThief = catchTheThief;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void sayIt() {
        IO.println();
        IO.println("===== \uD83D\uDD0DЗапутанное дело о краже кораллов:");
        catchTheThief.readOutTestimony();
        IO.println("   *Попробуйте вывести разные версии показаний о краже кораллов в строке выше, не меняя сами текстовые строки.");
        IO.println("   **Как минимум пять имён!");
        IO.println("=====");
        IO.println();
    }
}

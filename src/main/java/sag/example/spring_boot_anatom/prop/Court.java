package sag.example.spring_boot_anatom.prop;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

@Configuration
@PropertySource(value="classpath:custom.properties", encoding = "UTF-8")
public class Court {

    private final CatchTheThief catchTheThief;

    public Court(CatchTheThief catchTheThief) {
        this.catchTheThief = catchTheThief;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void sayIt() {
        Anatom.out(Slice.DETECTIVE, "");
        Anatom.out(Slice.DETECTIVE, "===== \uD83D\uDD0DЗапутанное дело о краже кораллов:");
        catchTheThief.readOutTestimony();
        Anatom.out(Slice.DETECTIVE, "   * Попробуйте вывести разные версии показаний о краже кораллов в строке выше, не меняя сами текстовые строки.");
        Anatom.out(Slice.DETECTIVE, "   Можно комментировать код, менять профили.");
        Anatom.out(Slice.DETECTIVE, "   ** Как минимум шесть имён!");
        Anatom.out(Slice.DETECTIVE, "=====");
        Anatom.out(Slice.DETECTIVE, "");
    }
}

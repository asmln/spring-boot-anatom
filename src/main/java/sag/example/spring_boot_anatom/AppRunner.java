package sag.example.spring_boot_anatom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

@Component
public class AppRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Anatom.out(Slice.SPRING, "---\uD83D\uDCBBCommandLineRunner.");
        Anatom.out(Slice.SPRING, "   Удобная штука, если надо попробовать что-то в Spring Boot.");
        Anatom.out(Slice.SPRING, "   Не нужны контроллеры, пишем код сюда, дёргаем бины, контекст...");
        Anatom.out(Slice.SPRING, "   ✅Незаменим для консольных приложений на Spring Boot. Хотя ApplicationRunner удобней для работы с аргументами командной строки.");
        Anatom.out(Slice.SPRING, "   Контекст Spring полностью сформирован, все бины созданы, веб-сервер (если используется) запустился.");
        Anatom.out(Slice.SPRING, "   Но официально этап старта не завершился.");
        Anatom.out(Slice.SPRING, "   ✅Хорошее место для прогрева кэшей. Для долгого прогрева лучше вызвать асинхронный метод.");
    }
}

package sag.example.spring_boot_anatom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        IO.println("---\uD83D\uDCBBCommandLineRunner.");
        IO.println("   Удобная штука, если надо попробовать что-то в Spring Boot.");
        IO.println("   Не нужны контроллеры, пишем код сюда, дёргаем бины, контекст...");
        IO.println("   ✅Незаменим для консольных приложений на Spring Boot. Хотя ApplicationRunner удобней для работы с аргументами командной строки.");
        IO.println("   Контекст Spring полностью сформирован, все бины созданы, веб-сервер (если используется) запустился.");
        IO.println("   Но официально этап старта не завершился.");
        IO.println("   ✅Хорошее место для прогрева кэшей. Для долгого прогрева лучше вызвать асинхронный метод.");
    }
}

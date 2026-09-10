package sag.example.spring_boot_anatom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        IO.println("---\uD83D\uDC49 CommandLineRunner.");
        IO.println("""
                Контекст Spring полностью сформирован, все бины созданы, веб-сервер (если используется) запустился.
                Но официально этап старта не завершился."""
        );
        IO.println("✅ Хорошее место для прогрева кэшей. Для долгого прогрева лучше вызвать асинхронный метод.");
    }
}

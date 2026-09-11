package sag.example.spring_boot_anatom;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;
import sag.example.spring_boot_anatom.events.ApplicationEnvironmentPreparedEventListener;
import sag.example.spring_boot_anatom.events.ApplicationStartingEventListener;

@SpringBootApplication
@EnableAsync
public class SpringBootAnatomApplication {
	static void main(String[] args) {
		IO.println("---\uD83D\uDE80SpringBootApplication main - самое начало старта.");
		IO.println("   Контекст Spring еще не создан.");
		IO.println("   Можно задать проперти, тайм-зону, какие-то общие настройки приложения.");
		IO.println("   Проверить окружение и выйти с ошибкой.");
		IO.println("   Обратиться за внешними настройками или секретами.");
		IO.println("   ❌Не пытайтесь обращаться к контексту или вызывать бины.");
		IO.println("   ❌Не выполняйте тяжёлые блокирующие операции.");
		// Можно просто вот так: SpringApplication.run(SpringBootAnatomApplication.class, args); но...
		SpringApplication app = new SpringApplication(SpringBootAnatomApplication.class);
		app.setBannerMode(Banner.Mode.OFF); // Можно отключить красивый баннер ☹️
		// И делать всякое с app.
		// Регистрируем слушателей событий, которые работают с событиями, происходящими до создания контекста
		app.addListeners(new ApplicationStartingEventListener());
		app.addListeners(new ApplicationEnvironmentPreparedEventListener());
		app.addListeners(
				(ApplicationListener<ApplicationFailedEvent>) event -> {
            		System.err.println(")\uD83D\uDCA5( что-то мы начудили...");
        		}
		);
		app.run(args);
	}
}

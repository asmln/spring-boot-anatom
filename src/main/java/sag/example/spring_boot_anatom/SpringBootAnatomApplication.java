package sag.example.spring_boot_anatom;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootAnatomApplication {

	static void main(String[] args) {
		IO.println("---\uD83D\uDC49 SpringBootApplication main - самое начало старта.");
		IO.println(
                """
                        Контекст Spring еще не создан.
                        Можно задать проперти, тайм-зону, какие-то общие настройки приложения.
                        Проверить окружение и выйти с ошибкой.
                        Обратиться за внешними настройками или секретами.
                        ❌Не пытайтесь обращаться к контексту или вызывать бины.
                        ❌Не выполняйте тяжёлые блокирующие операции."""
		);
		// Можно просто вот так: SpringApplication.run(SpringBootAnatomApplication.class, args); но...
		SpringApplication app = new SpringApplication(SpringBootAnatomApplication.class);
		//app.setBannerMode(Banner.Mode.OFF); // Можно отключить красивый баннер ☹️
		// И делать всякое с app.
		app.run(args);
	}

}

package sag.example.spring_boot_anatom.pp;

import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

import java.util.Map;

public class TadamEnvironmentPostProcessor implements EnvironmentPostProcessor {

    public TadamEnvironmentPostProcessor() {
        Anatom.out(Slice.SPRING,"---\uD83C\uDFED[EPP] Создание кастомного EnvironmentPostProcessor.");
        Anatom.out(Slice.SPRING,"   ✅[EPP] Штука, которая служит для динамического изменения ConfigurableEnvironment на самом раннем этапе запуска.");
        Anatom.out(Slice.SPRING,"   ✅[EPP] Удобно использовать в стартере, который должен задать какие-то пропертис по-умолчанию.");
        Anatom.out(Slice.SPRING,"   [EPP] ❌ Регистрировать через @Component нельзя. ✅ Можно регистрировать в resources/META-INF/spring.factories");
        Anatom.out(Slice.SPRING,"   [EPP] Работаем с ConfigurableEnvironment. Категорически нельзя ❌ запрашивать готовые бины из контекста.");
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // Новый источник с высшим приоритетом (addFirst) перекроет значение court.testimony.
        // Но можно добавить ещё один источник позже и перекрыть этот 😀
        environment
                .getPropertySources()
                .addFirst(new MapPropertySource("court-environment-post-processor", Map.of(
                        "court.testimony", "Двутавр у Клары украл кораллы."
                )));
    }
}

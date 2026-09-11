package sag.example.spring_boot_anatom.events;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        IO.println("---\uD83D\uDC25ApplicationEnvironmentPreparedEvent - среда окружения (Environment) уже создана и известны активные профили, но контекст ещё не создан.");
        IO.println("   ✅Можно подгрузить что-то в Environment или всё там основательно запутать \uD83D\uDE09");
        ConfigurableEnvironment env = event.getEnvironment();
        // Получим профили
        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        Map<String, Object> overrides = new HashMap<>();
        if (activeProfiles.isEmpty()) {
            overrides.put("court.testimony", "Вы ничего не докажете!");
        } else if (activeProfiles.contains("truth")) {
            overrides.put("court.testimony", "Знать ничего не знаю, гражданин начальник.");
        } else {
            return;
        }
        // Новый источник с высшим приоритетом перекроет значение court.testimony
        env.getPropertySources()
                .addFirst(new MapPropertySource("externalConfigOverrides", overrides));
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

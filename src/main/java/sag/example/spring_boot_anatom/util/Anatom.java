package sag.example.spring_boot_anatom.util;

import java.util.*;

public class Anatom {
    // Ключи для пристального изучения только одного среза. Если null, значит выводим всё.
    private static final Set<Slice> ACTIVE_SLICES;

    static {
        String sliceProp = System.getProperty("slice");
        if (sliceProp == null || sliceProp.trim().isEmpty()) {
            ACTIVE_SLICES = null; // Ключ не передан -> выводим всё
        } else {
            // Разрешаем передавать несколько ключей через запятую: -Dslice=bean,context
            ACTIVE_SLICES = EnumSet.copyOf(Arrays.stream(sliceProp.split(",")).map(String::toUpperCase).map(Slice::valueOf).toList());
        }
    }

    public static void out(Slice sliceKey, String message) {
        out(List.of(sliceKey), message);
    }

    public static void out(Collection<Slice> sliceKeys, String message) {
        if (ACTIVE_SLICES == null || sliceKeys.stream().anyMatch(ACTIVE_SLICES::contains)) {
            IO.println(message);
        }
    }
}

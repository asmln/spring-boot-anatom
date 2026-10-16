package sag.example.spring_boot_anatom.bean;

import sag.example.spring_boot_anatom.util.Anatom;
import sag.example.spring_boot_anatom.util.Slice;

public class BornToBeDestroyed {
    public void close() {
        Anatom.out(Slice.BEAN, "\uD83E\uDDEA[@Bean destroy] вызов bornToBeDestroyed.close() без реализации каких-либо интерфейсов или аннотаций над методом.");
    }
    public void shutdown() {
        // Не вызовется, т.к. есть close()
        Anatom.out(Slice.BEAN, "\uD83E\uDDEA[@Bean destroy] вызов bornToBeDestroyed.shutdown() без реализации каких-либо интерфейсов или аннотаций над методом.");
    }
}

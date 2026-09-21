package sag.example.spring_boot_anatom.bean;

public class BornToBeDestroyed {
    public void close() {
        IO.println("\uD83E\uDDEA[@Bean destroy] вызов bornToBeDestroyed.close() без реализации каких-либо интерфейсов или аннотаций над методом.");
    }
    public void shutdown() {
        // Не вызовется, т.к. есть close()
        IO.println("\uD83E\uDDEA[@Bean destroy] вызов bornToBeDestroyed.shutdown() без реализации каких-либо интерфейсов или аннотаций над методом.");
    }
}

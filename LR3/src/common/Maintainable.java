package common;

// ИНТЕРФЕЙС - определяет контракт для обслуживаемых объектов
public interface Maintainable {
    void performMaintenance();
    int getMaintenanceInterval();
    boolean needsMaintenance();
}
// ИНТЕРФЕЙС - определяет контракт для летающих объектов
public interface Flyable {
    void takeOff();
    void land();
    void fly(int altitude);
    double getFuelConsumption();
}

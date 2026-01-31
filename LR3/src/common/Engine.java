package common;

// HAS-A RELATIONSHIP - класс Engine для демонстрации агрегации
public class Engine {
    private String model;
    private int horsePower;
    private double fuelConsumptionRate;
    
    // ПЕРЕГРУЗКА КОНСТРУКТОРОВ
    public Engine() {
        this("Unknown", 1000, 5.0);
    }
    
    public Engine(String model) {
        this(model, 1000, 5.0);
    }
    
    public Engine(String model, int horsePower) {
        this(model, horsePower, 5.0);
    }
    
    public Engine(String model, int horsePower, double fuelConsumptionRate) {
        this.model = model;
        this.horsePower = horsePower;
        this.fuelConsumptionRate = fuelConsumptionRate;
    }
    
    // ПЕРЕГРУЗКА МЕТОДОВ
    public void start() {
        System.out.println("Engine " + model + " starting...");
    }
    
    public void start(boolean quickStart) {
        if (quickStart) {
            System.out.println("Engine " + model + " quick starting!");
        } else {
            start();
        }
    }
    
    public void stop() {
        System.out.println("Engine " + model + " stopped.");
    }
    
    public String getInfo() {
        return "Engine: " + model + ", HP: " + horsePower + ", Consumption: " + fuelConsumptionRate + " L/h";
    }
    
    public double getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }
    
    public int getHorsePower() {
        return horsePower;
    }
}
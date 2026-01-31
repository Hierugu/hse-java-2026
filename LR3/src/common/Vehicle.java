package common;

// АБСТРАКТНЫЙ КЛАСС - базовый класс для всех транспортных средств
public abstract class Vehicle {
    protected String manufacturer;
    protected String model;
    protected int year;
    protected double maxSpeed;
    
    // ПЕРЕГРУЗКА КОНСТРУКТОРОВ
    public Vehicle() {
        this("Unknown", "Unknown", 2024, 0.0);
    }
    
    public Vehicle(String manufacturer, String model) {
        this(manufacturer, model, 2024, 0.0);
    }
    
    public Vehicle(String manufacturer, String model, int year, double maxSpeed) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.maxSpeed = maxSpeed;
    }
    
    // ВИРТУАЛЬНЫЙ МЕТОД - может быть переопределен в подклассах
    public void displayInfo() {
        System.out.println("Vehicle: " + manufacturer + " " + model + " (" + year + ")");
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
    
    // АБСТРАКТНЫЕ МЕТОДЫ - должны быть реализованы в подклассах
    public abstract void start();
    public abstract void stop();
    public abstract String getType();
    
    // Геттеры
    public String getManufacturer() {
        return manufacturer;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getMaxSpeed() {
        return maxSpeed;
    }
}
// IS-A RELATIONSHIP - CargoPlane является Aeroplane
// НАСЛЕДОВАНИЕ - второй уровень иерархии
public class CargoPlane extends Aeroplane {
    private double maxCargoWeight;
    private double cargoVolumeCapacity;
    private boolean hasLoadingRamp;
    
    // ПЕРЕГРУЗКА КОНСТРУКТОРОВ
    public CargoPlane() {
        super();
        this.maxCargoWeight = 50000.0;
        this.cargoVolumeCapacity = 300.0;
        this.hasLoadingRamp = true;
    }
    
    public CargoPlane(String manufacturer, String model, int year, double maxSpeed,
                      double wingspan, Engine engine,
                      double maxCargoWeight, double cargoVolumeCapacity, boolean hasLoadingRamp) {
        super(manufacturer, model, year, maxSpeed, 0, wingspan, engine);
        this.maxCargoWeight = maxCargoWeight;
        this.cargoVolumeCapacity = cargoVolumeCapacity;
        this.hasLoadingRamp = hasLoadingRamp;
    }
    
    // ПЕРЕОПРЕДЕЛЕНИЕ МЕТОДА - демонстрация полиморфизма
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Max Cargo Weight: " + maxCargoWeight + " kg");
        System.out.println("Cargo Volume: " + cargoVolumeCapacity + " m³");
        System.out.println("Loading Ramp: " + (hasLoadingRamp ? "Yes" : "No"));
    }
    
    @Override
    public String getType() {
        return "Cargo Plane";
    }
    
    // ПЕРЕГРУЗКА МЕТОДОВ
    public void loadCargo() {
        System.out.println("Loading cargo...");
    }
    
    public void loadCargo(double weight) {
        if (weight > maxCargoWeight) {
            System.out.println("Warning: Cargo weight " + weight + " kg exceeds maximum " + maxCargoWeight + " kg!");
        } else {
            System.out.println("Loading " + weight + " kg of cargo.");
        }
    }
    
    public void loadCargo(double weight, String cargoType) {
        loadCargo(weight);
        System.out.println("Cargo type: " + cargoType);
    }
    
    public void unloadCargo() {
        System.out.println("Unloading cargo...");
    }
    
    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }
}

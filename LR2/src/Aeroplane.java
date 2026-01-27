// IS-A RELATIONSHIP - Aeroplane является Vehicle
// РЕАЛИЗАЦИЯ ИНТЕРФЕЙСОВ
public class Aeroplane extends Vehicle implements Flyable, Maintainable {
    private int passengerCapacity;
    private double wingspan;
    private Engine engine; // HAS-A RELATIONSHIP - агрегация
    private int flightHours;
    private boolean isFlying;
    
    // ПЕРЕГРУЗКА КОНСТРУКТОРОВ
    public Aeroplane() {
        super();
        this.passengerCapacity = 0;
        this.wingspan = 0.0;
        this.engine = new Engine();
        this.flightHours = 0;
        this.isFlying = false;
    }
    
    public Aeroplane(String manufacturer, String model) {
        super(manufacturer, model);
        this.passengerCapacity = 100;
        this.wingspan = 35.0;
        this.engine = new Engine();
        this.flightHours = 0;
        this.isFlying = false;
    }
    
    public Aeroplane(String manufacturer, String model, int year, double maxSpeed, 
                     int passengerCapacity, double wingspan) {
        super(manufacturer, model, year, maxSpeed);
        this.passengerCapacity = passengerCapacity;
        this.wingspan = wingspan;
        this.engine = new Engine();
        this.flightHours = 0;
        this.isFlying = false;
    }
    
    public Aeroplane(String manufacturer, String model, int year, double maxSpeed,
                     int passengerCapacity, double wingspan, Engine engine) {
        super(manufacturer, model, year, maxSpeed);
        this.passengerCapacity = passengerCapacity;
        this.wingspan = wingspan;
        this.engine = engine;
        this.flightHours = 0;
        this.isFlying = false;
    }
    
    // ПЕРЕОПРЕДЕЛЕНИЕ АБСТРАКТНЫХ МЕТОДОВ
    @Override
    public void start() {
        System.out.println("\n=== Starting " + manufacturer + " " + model + " ===");
        engine.start();
        System.out.println("Aircraft systems initialized.");
    }
    
    @Override
    public void stop() {
        if (isFlying) {
            System.out.println("Cannot stop while flying! Land first.");
            return;
        }
        System.out.println("\n=== Stopping " + manufacturer + " " + model + " ===");
        engine.stop();
        System.out.println("Aircraft systems shut down.");
    }
    
    @Override
    public String getType() {
        return "Aeroplane";
    }
    
    // ПЕРЕОПРЕДЕЛЕНИЕ ВИРТУАЛЬНОГО МЕТОДА
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Wingspan: " + wingspan + " m");
        System.out.println("Flight Hours: " + flightHours);
        System.out.println(engine.getInfo());
    }
    
    // РЕАЛИЗАЦИЯ ИНТЕРФЕЙСА Flyable
    @Override
    public void takeOff() {
        if (isFlying) {
            System.out.println("Already in the air!");
            return;
        }
        System.out.println("\n>>> " + model + " is taking off...");
        engine.start(true);
        System.out.println("Accelerating on runway...");
        System.out.println("Lifting off!");
        isFlying = true;
    }
    
    @Override
    public void land() {
        if (!isFlying) {
            System.out.println("Already on the ground!");
            return;
        }
        System.out.println("\n<<< " + model + " is landing...");
        System.out.println("Descending...");
        System.out.println("Touchdown!");
        System.out.println("Taxiing to gate.");
        isFlying = false;
        flightHours += 2; // Симуляция 2 часа полета
    }
    
    @Override
    public void fly(int altitude) {
        if (!isFlying) {
            System.out.println("Cannot fly - not in the air! Take off first.");
            return;
        }
        System.out.println("Flying at " + altitude + " meters altitude.");
    }
    
    @Override
    public double getFuelConsumption() {
        return engine.getFuelConsumptionRate() * passengerCapacity / 100.0;
    }
    
    // РЕАЛИЗАЦИЯ ИНТЕРФЕЙСА Maintainable
    @Override
    public void performMaintenance() {
        System.out.println("\n*** Performing maintenance on " + model + " ***");
        System.out.println("Checking engine...");
        System.out.println("Inspecting wings and fuselage...");
        System.out.println("Testing avionics...");
        System.out.println("Maintenance complete!");
        flightHours = 0; // Сброс счетчика после обслуживания
    }
    
    @Override
    public int getMaintenanceInterval() {
        return 100; // Обслуживание каждые 100 часов
    }
    
    @Override
    public boolean needsMaintenance() {
        return flightHours >= getMaintenanceInterval();
    }
    
    // ПЕРЕГРУЗКА МЕТОДОВ
    public void fly() {
        fly(10000); // По умолчанию 10000 метров
    }
    
    public void fly(int altitude, String direction) {
        fly(altitude);
        System.out.println("Heading: " + direction);
    }
    
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    
    public boolean isFlying() {
        return isFlying;
    }
    
    public int getFlightHours() {
        return flightHours;
    }
}

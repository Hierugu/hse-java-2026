// IS-A RELATIONSHIP - PassengerPlane является Aeroplane
// НАСЛЕДОВАНИЕ - второй уровень иерархии
public class PassengerPlane extends Aeroplane {
    private int businessClassSeats;
    private int economyClassSeats;
    private boolean hasWiFi;
    
    // ПЕРЕГРУЗКА КОНСТРУКТОРОВ
    public PassengerPlane() {
        super();
        this.businessClassSeats = 20;
        this.economyClassSeats = 180;
        this.hasWiFi = true;
    }
    
    public PassengerPlane(String manufacturer, String model, int year, double maxSpeed,
                          int passengerCapacity, double wingspan, Engine engine,
                          int businessClassSeats, int economyClassSeats, boolean hasWiFi) {
        super(manufacturer, model, year, maxSpeed, passengerCapacity, wingspan, engine);
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economyClassSeats;
        this.hasWiFi = hasWiFi;
    }
    
    // ПЕРЕОПРЕДЕЛЕНИЕ МЕТОДА - демонстрация полиморфизма
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Business Class Seats: " + businessClassSeats);
        System.out.println("Economy Class Seats: " + economyClassSeats);
        System.out.println("WiFi Available: " + (hasWiFi ? "Yes" : "No"));
    }
    
    @Override
    public String getType() {
        return "Passenger Plane";
    }
    
    // ПЕРЕГРУЗКА МЕТОДОВ
    public void boardPassengers() {
        System.out.println("Boarding passengers...");
        System.out.println("Business class boarding first.");
        System.out.println("Economy class now boarding.");
    }
    
    public void boardPassengers(int count) {
        System.out.println("Boarding " + count + " passengers...");
    }
    
    public void boardPassengers(int businessCount, int economyCount) {
        System.out.println("Boarding " + businessCount + " business and " + 
                         economyCount + " economy passengers.");
    }
    
    public void serveMeal() {
        System.out.println("Serving meals to passengers.");
    }
}

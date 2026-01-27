public class App {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("ДЕМОНСТРАЦИЯ ОБЪЕКТНОЙ МОДЕЛИ В JAVA - AEROPLANE");
        System.out.println("=".repeat(70));
        
        // ========== 1. ПЕРЕГРУЗКА КОНСТРУКТОРОВ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("1. ДЕМОНСТРАЦИЯ ПЕРЕГРУЗКИ КОНСТРУКТОРОВ");
        System.out.println("=".repeat(70));
        
        Engine engine1 = new Engine();
        Engine engine2 = new Engine("Rolls-Royce Trent 900");
        Engine engine3 = new Engine("CFM56-7B", 27300);
        Engine engine4 = new Engine("GE90-115B", 52000, 12.5);
        
        System.out.println("Конструктор без параметров: " + engine1.getInfo());
        System.out.println("Конструктор с 1 параметром: " + engine2.getInfo());
        System.out.println("Конструктор с 2 параметрами: " + engine3.getInfo());
        System.out.println("Конструктор с 3 параметрами: " + engine4.getInfo());
        
        // ========== 2. HAS-A RELATIONSHIP (Агрегация) ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("2. ДЕМОНСТРАЦИЯ HAS-A RELATIONSHIP (АГРЕГАЦИЯ)");
        System.out.println("=".repeat(70));
        
        Engine boeingEngine = new Engine("GE90-115B", 52000, 12.5);
        Aeroplane boeing777 = new Aeroplane("Boeing", "777-300ER", 2020, 905.0, 
                                            396, 64.8, boeingEngine);
        System.out.println("✓ Самолет Boeing 777 СОДЕРЖИТ (HAS-A) двигатель GE90-115B");
        
        // ========== 3. IS-A RELATIONSHIP (Наследование) ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("3. ДЕМОНСТРАЦИЯ IS-A RELATIONSHIP (НАСЛЕДОВАНИЕ)");
        System.out.println("=".repeat(70));
        
        Engine airbusEngine = new Engine("Rolls-Royce Trent 900", 36300, 11.8);
        PassengerPlane airbus380 = new PassengerPlane("Airbus", "A380", 2021, 945.0,
                                                       853, 79.8, airbusEngine,
                                                       100, 753, true);
        
        Engine cargoEngine = new Engine("GE CF6-80C2", 28000, 10.5);
        CargoPlane boeing747F = new CargoPlane("Boeing", "747-8F", 2019, 908.0,
                                                68.4, cargoEngine,
                                                140000.0, 858.0, true);
        
        System.out.println("✓ PassengerPlane IS-A Aeroplane IS-A Vehicle");
        System.out.println("✓ CargoPlane IS-A Aeroplane IS-A Vehicle");
        
        // ========== 4. ПОЛИМОРФИЗМ И ВИРТУАЛЬНЫЕ МЕТОДЫ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("4. ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА И ВИРТУАЛЬНЫХ МЕТОДОВ");
        System.out.println("=".repeat(70));
        
        // Массив типа Vehicle, содержащий разные типы самолетов
        Vehicle[] fleet = new Vehicle[] {
            boeing777,
            airbus380,
            boeing747F
        };
        
        System.out.println("Полиморфное поведение - вызов методов через базовый тип:");
        for (int i = 0; i < fleet.length; i++) {
            System.out.println("\n--- Объект " + (i + 1) + " ---");
            fleet[i].displayInfo(); // Виртуальный метод - вызовется переопределенная версия
            System.out.println("Type: " + fleet[i].getType());
        }
        
        // ========== 5. ИНТЕРФЕЙСЫ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("5. ДЕМОНСТРАЦИЯ ИНТЕРФЕЙСОВ");
        System.out.println("=".repeat(70));
        
        // Массив интерфейсного типа Flyable
        Flyable[] flyingObjects = new Flyable[] {
            boeing777,
            airbus380,
            boeing747F
        };
        
        System.out.println("Работа через интерфейс Flyable:");
        for (Flyable flyable : flyingObjects) {
            System.out.println("\nFuel consumption: " + flyable.getFuelConsumption() + " L/h");
        }
        
        // ========== 6. ПЕРЕГРУЗКА МЕТОДОВ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("6. ДЕМОНСТРАЦИЯ ПЕРЕГРУЗКИ МЕТОДОВ");
        System.out.println("=".repeat(70));
        
        System.out.println("\nПерегрузка метода fly():");
        boeing777.takeOff();
        boeing777.fly();                    // fly() без параметров
        boeing777.fly(12000);               // fly(int altitude)
        boeing777.fly(11000, "North-East"); // fly(int altitude, String direction)
        boeing777.land();
        
        System.out.println("\nПерегрузка метода boardPassengers():");
        airbus380.boardPassengers();           // без параметров
        airbus380.boardPassengers(850);        // с одним параметром
        airbus380.boardPassengers(100, 750);   // с двумя параметрами
        
        System.out.println("\nПерегрузка метода loadCargo():");
        boeing747F.loadCargo();                      // без параметров
        boeing747F.loadCargo(120000.0);              // с весом
        boeing747F.loadCargo(130000.0, "Electronics"); // с весом и типом
        
        // ========== 7. АБСТРАКТНЫЕ КЛАССЫ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("7. ДЕМОНСТРАЦИЯ АБСТРАКТНЫХ КЛАССОВ");
        System.out.println("=".repeat(70));
        
        System.out.println("Vehicle - абстрактный класс с абстрактными методами:");
        System.out.println("- abstract void start()");
        System.out.println("- abstract void stop()");
        System.out.println("- abstract String getType()");
        System.out.println("\nНельзя создать экземпляр: new Vehicle() - ошибка компиляции");
        System.out.println("Подклассы обязаны реализовать абстрактные методы.");
        
        // ========== 8. ПОЛНЫЙ ЦИКЛ РАБОТЫ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("8. ПОЛНЫЙ ЦИКЛ РАБОТЫ С ОБЪЕКТАМИ");
        System.out.println("=".repeat(70));
        
        System.out.println("\n--- Пассажирский рейс ---");
        airbus380.start();
        airbus380.takeOff();
        airbus380.fly(11000, "Paris");
        airbus380.serveMeal();
        airbus380.land();
        airbus380.stop();
        
        System.out.println("\n--- Грузовой рейс ---");
        boeing747F.start();
        boeing747F.loadCargo(135000.0, "Medical supplies");
        boeing747F.takeOff();
        boeing747F.fly(10500);
        boeing747F.land();
        boeing747F.unloadCargo();
        boeing747F.stop();
        
        // ========== 9. ОБСЛУЖИВАНИЕ (Интерфейс Maintainable) ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("9. ДЕМОНСТРАЦИЯ ИНТЕРФЕЙСА MAINTAINABLE");
        System.out.println("=".repeat(70));
        
        Maintainable[] maintenanceQueue = new Maintainable[] {
            boeing777,
            airbus380,
            boeing747F
        };
        
        System.out.println("\nПроверка необходимости обслуживания:");
        for (int i = 0; i < maintenanceQueue.length; i++) {
            Maintainable item = maintenanceQueue[i];
            Aeroplane plane = (Aeroplane) item; // Приведение типа
            
            System.out.println("\n" + plane.getModel() + ":");
            System.out.println("Flight hours: " + plane.getFlightHours());
            System.out.println("Maintenance interval: " + item.getMaintenanceInterval() + " hours");
            System.out.println("Needs maintenance: " + (item.needsMaintenance() ? "YES" : "NO"));
            
            if (item.needsMaintenance()) {
                item.performMaintenance();
            }
        }
        
        // ========== 10. ИНКАПСУЛЯЦИЯ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("10. ДЕМОНСТРАЦИЯ ИНКАПСУЛЯЦИИ");
        System.out.println("=".repeat(70));
        
        System.out.println("\nВсе поля классов объявлены как private:");
        System.out.println("- Прямой доступ к полям запрещен");
        System.out.println("- Доступ осуществляется через публичные методы (геттеры)");
        System.out.println("\nПример:");
        System.out.println("boeing777.getManufacturer() = " + boeing777.getManufacturer());
        System.out.println("boeing777.getMaxSpeed() = " + boeing777.getMaxSpeed() + " km/h");
        System.out.println("boeing777.getPassengerCapacity() = " + boeing777.getPassengerCapacity());
        
        // ========== ИТОГИ ==========
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ИТОГОВАЯ СВОДКА");
        System.out.println("=".repeat(70));
        System.out.println("✓ Конструкторы и их перегрузка - реализовано");
        System.out.println("✓ Перегрузка методов - реализовано");
        System.out.println("✓ Наследование IS-A - реализовано (Vehicle → Aeroplane → PassengerPlane/CargoPlane)");
        System.out.println("✓ Агрегация HAS-A - реализовано (Aeroplane содержит Engine)");
        System.out.println("✓ Виртуальные методы - реализовано (displayInfo, start, stop, getType)");
        System.out.println("✓ Абстрактные классы - реализовано (Vehicle)");
        System.out.println("✓ Полиморфизм - реализовано (массивы базового типа)");
        System.out.println("✓ Интерфейсы - реализовано (Flyable, Maintainable)");
        System.out.println("✓ Инкапсуляция - реализовано (private поля, public методы)");
        System.out.println("=".repeat(70));
    }
}

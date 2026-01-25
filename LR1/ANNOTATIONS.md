# КОММЕНТАРИИ К КОДУ - ГДЕ ЧТО РЕАЛИЗОВАНО

## 1. ПЕРЕГРУЗКА КОНСТРУКТОРОВ

### Engine.java (строки 7-24)
- Конструктор без параметров (строка 7)
- Конструктор с 1 параметром (строка 11)
- Конструктор с 2 параметрами (строка 15)
- Конструктор с 3 параметрами (строка 19)

### Vehicle.java (строки 7-21)
- Конструктор без параметров (строка 7)
- Конструктор с 2 параметрами (строка 11)
- Конструктор с 4 параметрами (строка 15)

### Aeroplane.java (строки 11-46)
- 4 перегруженных конструктора для создания самолетов с разным набором параметров

### PassengerPlane.java (строки 7-21)
- 2 перегруженных конструктора

### CargoPlane.java (строки 7-20)
- 2 перегруженных конструктора

---

## 2. ПЕРЕГРУЗКА МЕТОДОВ

### Engine.java (строки 26-35)
```java
public void start()                    // строка 26
public void start(boolean quickStart)  // строка 30
```

### Aeroplane.java (строки 131-142)
```java
public void fly()                              // строка 131
public void fly(int altitude, String direction) // строка 135
```
(метод fly(int altitude) от интерфейса Flyable на строке 102)

### PassengerPlane.java (строки 40-51)
```java
public void boardPassengers()                          // строка 40
public void boardPassengers(int count)                 // строка 45
public void boardPassengers(int businessCount, ...)    // строка 49
```

### CargoPlane.java (строки 40-54)
```java
public void loadCargo()                           // строка 40
public void loadCargo(double weight)              // строка 44
public void loadCargo(double weight, String type) // строка 51
```

---

## 3. НАСЛЕДОВАНИЕ (IS-A RELATIONSHIP)

### Vehicle.java (строка 2)
```java
public abstract class Vehicle { ... }
```
Абстрактный базовый класс для всех транспортных средств

### Aeroplane.java (строка 3)
```java
public class Aeroplane extends Vehicle implements Flyable, Maintainable
```
**IS-A**: Aeroplane ЯВЛЯЕТСЯ Vehicle

### PassengerPlane.java (строка 3)
```java
public class PassengerPlane extends Aeroplane
```
**IS-A**: PassengerPlane ЯВЛЯЕТСЯ Aeroplane (а значит и Vehicle)

### CargoPlane.java (строка 3)
```java
public class CargoPlane extends Aeroplane
```
**IS-A**: CargoPlane ЯВЛЯЕТСЯ Aeroplane (а значит и Vehicle)

**Иерархия наследования:**
```
Vehicle (абстрактный)
  └── Aeroplane
       ├── PassengerPlane
       └── CargoPlane
```

---

## 4. АГРЕГАЦИЯ (HAS-A RELATIONSHIP)

### Aeroplane.java (строка 8)
```java
private Engine engine; // HAS-A RELATIONSHIP - агрегация
```

**HAS-A**: Самолет СОДЕРЖИТ двигатель
- Двигатель передается в конструктор (строки 39-45)
- Самолет использует методы двигателя (строки 50, 64, 84, 103)

---

## 5. ВИРТУАЛЬНЫЕ МЕТОДЫ

### Vehicle.java (строки 23-27)
```java
// ВИРТУАЛЬНЫЙ МЕТОД - может быть переопределен в подклассах
public void displayInfo() { ... }
```

### Aeroplane.java (строка 68)
```java
@Override
public void displayInfo() {
    super.displayInfo();  // Вызов родительской версии
    // Дополнительная информация
}
```

### PassengerPlane.java (строка 24)
```java
@Override
public void displayInfo() {
    super.displayInfo();  // Вызов версии из Aeroplane
    // Дополнительная информация о пассажирском самолете
}
```

### CargoPlane.java (строка 23)
```java
@Override
public void displayInfo() {
    super.displayInfo();  // Вызов версии из Aeroplane
    // Дополнительная информация о грузовом самолете
}
```

---

## 6. АБСТРАКТНЫЕ КЛАССЫ

### Vehicle.java (строки 2, 30-32)
```java
public abstract class Vehicle {  // строка 2
    
    // АБСТРАКТНЫЕ МЕТОДЫ - должны быть реализованы в подклассах
    public abstract void start();     // строка 30
    public abstract void stop();      // строка 31
    public abstract String getType(); // строка 32
}
```

**Нельзя создать экземпляр абстрактного класса:**
```java
Vehicle v = new Vehicle(); // ОШИБКА КОМПИЛЯЦИИ!
```

### Реализация абстрактных методов в Aeroplane.java (строки 48-66)
```java
@Override
public void start() { ... }   // строка 48

@Override
public void stop() { ... }    // строка 58

@Override
public String getType() { ... } // строка 66
```

---

## 7. ПОЛИМОРФИЗМ

### App.java (строки 57-66)
```java
// Массив типа Vehicle, содержащий разные типы самолетов
Vehicle[] fleet = new Vehicle[] {
    boeing777,      // реальный тип: Aeroplane
    airbus380,      // реальный тип: PassengerPlane
    boeing747F      // реальный тип: CargoPlane
};

for (int i = 0; i < fleet.length; i++) {
    fleet[i].displayInfo(); // Вызовется метод соответствующего класса!
    System.out.println("Type: " + fleet[i].getType());
}
```

### App.java (строки 75-82)
```java
// Массив интерфейсного типа Flyable
Flyable[] flyingObjects = new Flyable[] {
    boeing777,
    airbus380,
    boeing747F
};

for (Flyable flyable : flyingObjects) {
    System.out.println("\nFuel consumption: " + flyable.getFuelConsumption() + " L/h");
}
```

---

## 8. ИНТЕРФЕЙСЫ

### Flyable.java
```java
public interface Flyable {
    void takeOff();
    void land();
    void fly(int altitude);
    double getFuelConsumption();
}
```

### Maintainable.java
```java
public interface Maintainable {
    void performMaintenance();
    int getMaintenanceInterval();
    boolean needsMaintenance();
}
```

### Реализация в Aeroplane.java (строка 3)
```java
public class Aeroplane extends Vehicle implements Flyable, Maintainable
```

#### Методы интерфейса Flyable (строки 77-113)
```java
@Override
public void takeOff() { ... }        // строка 77

@Override
public void land() { ... }           // строка 88

@Override
public void fly(int altitude) { ... } // строка 102

@Override
public double getFuelConsumption() { ... } // строка 110
```

#### Методы интерфейса Maintainable (строки 115-129)
```java
@Override
public void performMaintenance() { ... }  // строка 115

@Override
public int getMaintenanceInterval() { ... } // строка 125

@Override
public boolean needsMaintenance() { ... } // строка 129
```

---

## 9. ИНКАПСУЛЯЦИЯ

### Все классы используют private поля:

#### Vehicle.java (строки 3-6)
```java
protected String manufacturer;  // protected для доступа в подклассах
protected String model;
protected int year;
protected double maxSpeed;
```

#### Aeroplane.java (строки 4-9)
```java
private int passengerCapacity;
private double wingspan;
private Engine engine;
private int flightHours;
private boolean isFlying;
```

### Доступ через публичные методы (геттеры):

#### Vehicle.java (строки 34-50)
```java
public String getManufacturer() { return manufacturer; }
public String getModel() { return model; }
public int getYear() { return year; }
public double getMaxSpeed() { return maxSpeed; }
```

#### Aeroplane.java (строки 144-154)
```java
public int getPassengerCapacity() { return passengerCapacity; }
public boolean isFlying() { return isFlying; }
public int getFlightHours() { return flightHours; }
```

**Прямой доступ к полям запрещен:**
```java
// aeroplane.passengerCapacity = 500; // ОШИБКА! Поле private
int capacity = aeroplane.getPassengerCapacity(); // ПРАВИЛЬНО!
```

---

## 10. ДЕМОНСТРАЦИЯ ВСЕХ КОНЦЕПЦИЙ В App.java

Программа организована в 10 секций, каждая демонстрирует определенную концепцию:

1. **Строки 10-22**: Перегрузка конструкторов
2. **Строки 24-31**: HAS-A Relationship
3. **Строки 33-48**: IS-A Relationship
4. **Строки 50-71**: Полиморфизм и виртуальные методы
5. **Строки 73-84**: Интерфейсы
6. **Строки 86-104**: Перегрузка методов
7. **Строки 106-115**: Абстрактные классы
8. **Строки 117-143**: Полный цикл работы
9. **Строки 145-171**: Интерфейс Maintainable
10. **Строки 173-184**: Инкапсуляция

---

## КРАТКАЯ СВОДКА

| Концепция ООП | Файлы | Ключевые строки |
|---------------|-------|-----------------|
| **Перегрузка конструкторов** | Engine.java, Vehicle.java, Aeroplane.java, PassengerPlane.java, CargoPlane.java | Engine: 7-24, Vehicle: 7-21, Aeroplane: 11-46 |
| **Перегрузка методов** | Engine.java, Aeroplane.java, PassengerPlane.java, CargoPlane.java | Engine.start: 26-35, Aeroplane.fly: 131-142 |
| **IS-A (Наследование)** | Vehicle.java, Aeroplane.java, PassengerPlane.java, CargoPlane.java | extends Vehicle/Aeroplane |
| **HAS-A (Агрегация)** | Aeroplane.java | строка 8 (private Engine engine) |
| **Виртуальные методы** | Vehicle.java, Aeroplane.java, PassengerPlane.java, CargoPlane.java | displayInfo() |
| **Абстрактные классы** | Vehicle.java | строки 2, 30-32 |
| **Полиморфизм** | App.java | строки 57-82 |
| **Интерфейсы** | Flyable.java, Maintainable.java, Aeroplane.java | Реализация: 77-129 |
| **Инкапсуляция** | Все классы | private поля + public геттеры |

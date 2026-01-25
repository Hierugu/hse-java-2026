# Лабораторная работа: Объектная модель в Java

## Вариант: Aeroplane (Самолет)

## Описание проекта

Данный проект демонстрирует все основные концепции объектно-ориентированного программирования в Java на примере класса Aeroplane (Самолет) и связанных с ним классов.

## Структура проекта

```
src/
├── Flyable.java          - Интерфейс для летающих объектов
├── Maintainable.java     - Интерфейс для обслуживаемых объектов
├── Engine.java           - Класс двигателя (HAS-A relationship)
├── Vehicle.java          - Абстрактный базовый класс транспортного средства
├── Aeroplane.java        - Основной класс самолета
├── PassengerPlane.java   - Пассажирский самолет (IS-A relationship)
├── CargoPlane.java       - Грузовой самолет (IS-A relationship)
└── App.java              - Главный класс с демонстрацией всех концепций
```

## Реализованные концепции ООП

### 1. **Конструкторы и их перегрузка**
Реализовано в классах: `Engine`, `Vehicle`, `Aeroplane`, `PassengerPlane`, `CargoPlane`

Пример из `Engine.java`:
```java
public Engine() { ... }
public Engine(String model) { ... }
public Engine(String model, int horsePower) { ... }
public Engine(String model, int horsePower, double fuelConsumptionRate) { ... }
```

### 2. **Перегрузка методов**
Реализовано в классах: `Engine`, `Aeroplane`, `PassengerPlane`, `CargoPlane`

Пример из `Aeroplane.java`:
```java
public void fly() { ... }
public void fly(int altitude) { ... }
public void fly(int altitude, String direction) { ... }
```

### 3. **Наследование (IS-A Relationship)**
Иерархия наследования:
```
Vehicle (абстрактный)
  └── Aeroplane
       ├── PassengerPlane
       └── CargoPlane
```

- `Aeroplane IS-A Vehicle`
- `PassengerPlane IS-A Aeroplane`
- `CargoPlane IS-A Aeroplane`

### 4. **Агрегация (HAS-A Relationship)**
Реализовано в классе `Aeroplane`:
```java
private Engine engine; // Самолет СОДЕРЖИТ двигатель
```

### 5. **Виртуальные методы**
Методы, которые могут быть переопределены в подклассах:
- `displayInfo()` - переопределяется во всех подклассах
- `start()`, `stop()`, `getType()` - абстрактные методы, обязательны для реализации

### 6. **Абстрактные классы**
Класс `Vehicle` объявлен как абстрактный:
```java
public abstract class Vehicle {
    public abstract void start();
    public abstract void stop();
    public abstract String getType();
}
```

### 7. **Полиморфизм**
Демонстрация в `App.java`:
```java
Vehicle[] fleet = new Vehicle[] {
    boeing777,      // тип Aeroplane
    airbus380,      // тип PassengerPlane
    boeing747F      // тип CargoPlane
};

for (Vehicle v : fleet) {
    v.displayInfo(); // Вызовется соответствующая версия метода
}
```

### 8. **Интерфейсы**
Два интерфейса:
- `Flyable` - определяет поведение летающих объектов
- `Maintainable` - определяет поведение обслуживаемых объектов

Класс `Aeroplane` реализует оба интерфейса:
```java
public class Aeroplane extends Vehicle implements Flyable, Maintainable
```

### 9. **Инкапсуляция**
Все поля классов объявлены как `private`, доступ осуществляется через публичные методы:
```java
private String manufacturer;
private int passengerCapacity;

public String getManufacturer() { return manufacturer; }
public int getPassengerCapacity() { return passengerCapacity; }
```

## Компиляция и запуск

### Компиляция всех файлов:
```bash
cd src
javac *.java
```

### Запуск программы:
```bash
java App
```

### Или одной командой (PowerShell):
```powershell
cd src ; javac *.java ; if ($?) { java App }
```

## Результаты работы программы

Программа демонстрирует:

1. **Перегрузку конструкторов** - создание объектов Engine с разным количеством параметров
2. **HAS-A и IS-A relationships** - создание самолетов с двигателями и иерархия классов
3. **Полиморфизм** - работа с массивом базового типа Vehicle
4. **Интерфейсы** - работа через интерфейсы Flyable и Maintainable
5. **Перегрузку методов** - вызов методов fly(), boardPassengers(), loadCargo() с разными параметрами
6. **Абстрактные классы** - невозможность создания экземпляра Vehicle
7. **Полный цикл работы** - запуск, взлет, полет, посадка, остановка самолетов
8. **Обслуживание** - проверка необходимости технического обслуживания
9. **Инкапсуляцию** - доступ к данным через геттеры

## Архитектура решения

### Иерархия классов:
```
┌─────────────┐
│   Vehicle   │ (абстрактный класс)
└──────┬──────┘
       │
   ┌───▼────┐
   │Aeroplane│ ◄─────── содержит ───────┐
   └───┬────┘                            │
       │                          ┌──────▼──────┐
   ┌───┴──────┬──────┐            │   Engine    │
   │          │      │            └─────────────┘
┌──▼──┐  ┌───▼───┐  │
│Pass │  │Cargo  │  │
│enger│  │Plane  │  │
│Plane│  └───────┘  │
└─────┘             │
                    │
           ┌────────┴────────┐
           │  ┌──────────┐   │
           │  │ Flyable  │   │ (интерфейсы)
           │  └──────────┘   │
           │  ┌────────────┐ │
           └─ │Maintainable│ │
              └────────────┘ │
```

## Ключевые особенности реализации

1. **Множественная перегрузка конструкторов** - каждый класс имеет несколько конструкторов для гибкого создания объектов
2. **Цепочка вызовов конструкторов** - использование `super()` для вызова конструктора родительского класса
3. **Реализация множественных интерфейсов** - класс Aeroplane реализует Flyable и Maintainable
4. **Управление состоянием** - отслеживание состояния полета (`isFlying`) и часов налета (`flightHours`)
5. **Валидация операций** - проверка возможности выполнения операций (нельзя остановить двигатель в полете)

## Выводы

В ходе выполнения лабораторной работы были изучены и практически реализованы все основные концепции объектно-ориентированного программирования в Java:

1. **Инкапсуляция** обеспечивает скрытие внутренней реализации и защиту данных
2. **Наследование** позволяет создавать иерархии классов и переиспользовать код
3. **Полиморфизм** обеспечивает гибкость и расширяемость системы
4. **Абстракция** позволяет определять общие характеристики без конкретной реализации
5. **Интерфейсы** определяют контракты поведения объектов
6. **Перегрузка** методов и конструкторов обеспечивает удобство использования классов

Проект демонстрирует полноценную объектную модель авиационной тематики с возможностью расширения и добавления новых типов транспортных средств и функциональности.

---

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

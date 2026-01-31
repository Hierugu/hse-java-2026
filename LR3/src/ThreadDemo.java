import common.Aeroplane;
import common.Engine;

/**
 * Демонстрация работы с потоками через наследование от класса Thread
 * 
 * Задание 1: Создание потоков с использованием класса Thread
 * 
 * Ключевые концепции:
 * 1. Наследование от Thread
 * 2. Переопределение метода run()
 * 3. Запуск потоков через start()
 * 4. Параллельное выполнение потоков
 * 5. Использование Thread.sleep() для имитации длительных операций
 * 6. Именование потоков для идентификации
 */
public class ThreadDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ЗАДАНИЕ 1: ДЕМОНСТРАЦИЯ РАБОТЫ С КЛАССОМ THREAD");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // Создаем несколько самолетов для демонстрации
        Aeroplane boeing737 = new Aeroplane(
            "Boeing", "737-800", 2015, 850.0,
            189, 35.8, new Engine("CFM56-7B", 27300)
        );
        
        Aeroplane airbusA320 = new Aeroplane(
            "Airbus", "A320", 2018, 840.0,
            180, 35.8, new Engine("CFM56-5B", 27000)
        );
        
        Aeroplane boeing777 = new Aeroplane(
            "Boeing", "777-300ER", 2020, 905.0,
            396, 64.8, new Engine("GE90-115B", 52000)
        );
        
        System.out.println("Создано 3 самолета для демонстрации параллельной работы потоков\n");
        
        // ========================================================================
        // ПРИМЕР 1: Базовое создание и запуск потоков
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 1: Базовое создание и запуск потоков");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: создание потоков через наследование от Thread");
        System.out.println("Запускаем 2 потока обслуживания параллельно\n");
        
        // Создаем потоки обслуживания
        PlaneMaintenanceThread maintenance1 = new PlaneMaintenanceThread(boeing737);
        PlaneMaintenanceThread maintenance2 = new PlaneMaintenanceThread(airbusA320);
        
        // Запускаем потоки
        maintenance1.start();
        maintenance2.start();
        
        // Ждем завершения потоков
        try {
            maintenance1.join();
            maintenance2.join();
            System.out.println("\n✓ Оба потока обслуживания завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 2: Потоки с пользовательскими именами
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 2: Использование пользовательских имен потоков");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: именование потоков для лучшей идентификации\n");
        
        PlaneMaintenanceThread customMaintenance = new PlaneMaintenanceThread(
            boeing777, "CustomMaintenance-B777"
        );
        
        customMaintenance.start();
        
        try {
            customMaintenance.join();
            System.out.println("\n✓ Поток с пользовательским именем завершен");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 3: Параллельное выполнение разных типов потоков
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 3: Параллельное выполнение разных типов потоков");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: одновременное обслуживание и полет разных самолетов\n");
        
        // Создаем потоки разных типов
        FlightSimulatorThread flight1 = new FlightSimulatorThread(boeing737, "Moscow");
        PlaneMaintenanceThread maintenance3 = new PlaneMaintenanceThread(airbusA320);
        FlightSimulatorThread flight2 = new FlightSimulatorThread(boeing777, "London");
        
        // Запускаем все потоки одновременно
        flight1.start();
        maintenance3.start();
        flight2.start();
        
        // Ждем завершения всех потоков
        try {
            flight1.join();
            maintenance3.join();
            flight2.join();
            System.out.println("\n✓ Все потоки завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 4: Информация о потоках
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 4: Получение информации о потоках");
        System.out.println("=".repeat(80));
        
        PlaneMaintenanceThread infoThread = new PlaneMaintenanceThread(boeing737);
        
        System.out.println("Информация о потоке ДО запуска:");
        System.out.println("  - Имя: " + infoThread.getName());
        System.out.println("  - ID: " + infoThread.getId());
        System.out.println("  - Состояние: " + infoThread.getState());
        System.out.println("  - Приоритет: " + infoThread.getPriority());
        System.out.println("  - Жив: " + infoThread.isAlive());
        
        infoThread.start();
        
        System.out.println("\nИнформация о потоке ПОСЛЕ запуска:");
        System.out.println("  - Состояние: " + infoThread.getState());
        System.out.println("  - Жив: " + infoThread.isAlive());
        
        try {
            infoThread.join();
            System.out.println("\nИнформация о потоке ПОСЛЕ завершения:");
            System.out.println("  - Состояние: " + infoThread.getState());
            System.out.println("  - Жив: " + infoThread.isAlive());
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 5: Множественные потоки одного типа
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 5: Запуск множественных потоков одного типа");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: симуляция работы аэропорта с несколькими рейсами\n");
        
        // Создаем массив самолетов
        Aeroplane[] fleet = {
            new Aeroplane("Boeing", "737-700", 2016, 840.0, 149, 35.8),
            new Aeroplane("Airbus", "A319", 2017, 830.0, 156, 34.1),
            new Aeroplane("Boeing", "737-900", 2019, 850.0, 220, 35.8)
        };
        
        String[] destinations = {"Paris", "Berlin", "Rome"};
        
        // Создаем и запускаем потоки для каждого самолета
        FlightSimulatorThread[] flights = new FlightSimulatorThread[fleet.length];
        
        for (int i = 0; i < fleet.length; i++) {
            flights[i] = new FlightSimulatorThread(fleet[i], destinations[i]);
            flights[i].start();
        }
        
        // Ждем завершения всех полетов
        try {
            for (FlightSimulatorThread flight : flights) {
                flight.join();
            }
            System.out.println("\n✓ Все рейсы завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ИТОГИ
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ИТОГИ ДЕМОНСТРАЦИИ");
        System.out.println("=".repeat(80));
        System.out.println("\nПродемонстрированные концепции:");
        System.out.println("✓ 1. Создание потоков через наследование от Thread");
        System.out.println("✓ 2. Переопределение метода run()");
        System.out.println("✓ 3. Запуск потоков через start()");
        System.out.println("✓ 4. Параллельное выполнение нескольких потоков");
        System.out.println("✓ 5. Использование join() для ожидания завершения");
        System.out.println("✓ 6. Именование потоков для идентификации");
        System.out.println("✓ 7. Получение информации о состоянии потоков");
        System.out.println("✓ 8. Обработка InterruptedException");
        System.out.println("\nГлавный поток (main) завершен!");
        System.out.println("=".repeat(80));
    }
}
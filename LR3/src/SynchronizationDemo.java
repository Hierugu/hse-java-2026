import common.Aeroplane;
import common.Engine;

/**
 * Демонстрация синхронизации потоков
 * 
 * Задание 4: Синхронизация потоков выполнения
 * 
 * Ключевые концепции:
 * 1. Проблема race condition
 * 2. Ключевое слово synchronized
 * 3. Методы wait() и notify()
 * 4. Паттерн Producer-Consumer
 * 5. Взаимное исключение (mutual exclusion)
 */
public class SynchronizationDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ЗАДАНИЕ 4: ДЕМОНСТРАЦИЯ СИНХРОНИЗАЦИИ ПОТОКОВ");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // ========================================================================
        // ПРИМЕР 1: Проблема Race Condition
        // ========================================================================
        System.out.println("=".repeat(80));
        System.out.println("ПРИМЕР 1: Демонстрация проблемы Race Condition");
        System.out.println("=".repeat(80));
        System.out.println("Проблема: несколько потоков изменяют общий ресурс без синхронизации\n");
        
        demonstrateRaceCondition();
        
        // ========================================================================
        // ПРИМЕР 2: Решение через synchronized
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 2: Решение проблемы через synchronized");
        System.out.println("=".repeat(80));
        System.out.println("Решение: использование synchronized для защиты критической секции\n");
        
        demonstrateSynchronizedSolution();
        
        // ========================================================================
        // ПРИМЕР 3: Синхронизация доступа к взлетно-посадочной полосе
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 3: Синхронизация доступа к общему ресурсу");
        System.out.println("=".repeat(80));
        System.out.println("Сценарий: несколько самолетов используют одну взлетно-посадочную полосу\n");
        
        demonstrateRunwayControl();
        
        // ========================================================================
        // ПРИМЕР 4: Паттерн Producer-Consumer с wait/notify
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 4: Паттерн Producer-Consumer");
        System.out.println("=".repeat(80));
        System.out.println("Сценарий: поставщики заправляют резервуар, самолеты забирают топливо\n");
        
        demonstrateProducerConsumer();
        
        // ========================================================================
        // ПРИМЕР 5: Комплексная синхронизация
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 5: Комплексная синхронизация операций аэропорта");
        System.out.println("=".repeat(80));
        System.out.println("Сценарий: координация взлетов, посадок и заправки\n");
        
        demonstrateComplexSynchronization();
        
        // ========================================================================
        // ИТОГИ
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ИТОГИ ДЕМОНСТРАЦИИ");
        System.out.println("=".repeat(80));
        System.out.println("\nПродемонстрированные концепции:");
        System.out.println("✓ 1. Проблема race condition и ее последствия");
        System.out.println("✓ 2. Использование synchronized для защиты критических секций");
        System.out.println("✓ 3. Методы wait() и notify() для координации потоков");
        System.out.println("✓ 4. Паттерн Producer-Consumer");
        System.out.println("✓ 5. Взаимное исключение при доступе к общим ресурсам");
        System.out.println("✓ 6. Практические сценарии синхронизации");
        
        System.out.println("\nГлавный поток (main) завершен!");
        System.out.println("=".repeat(80));
    }
    
    /**
     * Демонстрация проблемы race condition
     */
    private static void demonstrateRaceCondition() {
        final int ITERATIONS = 1000;
        final int THREAD_COUNT = 10;
        
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        
        System.out.println("Запуск " + THREAD_COUNT + " потоков, каждый увеличивает счетчик " + 
                         ITERATIONS + " раз");
        System.out.println("Ожидаемый результат: " + (THREAD_COUNT * ITERATIONS));
        
        Thread[] threads = new Thread[THREAD_COUNT];
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    unsafeCounter.increment();
                }
            }, "UnsafeThread-" + (i + 1));
            threads[i].start();
        }
        
        // Ждем завершения всех потоков
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Фактический результат: " + unsafeCounter.getCount());
        System.out.println("❌ Результат НЕВЕРНЫЙ из-за race condition!");
        System.out.println("   Потоки одновременно изменяли счетчик без синхронизации");
    }
    
    /**
     * Демонстрация решения через synchronized
     */
    private static void demonstrateSynchronizedSolution() {
        final int ITERATIONS = 1000;
        final int THREAD_COUNT = 10;
        
        SafeCounter safeCounter = new SafeCounter();
        
        System.out.println("Запуск " + THREAD_COUNT + " потоков, каждый увеличивает счетчик " + 
                         ITERATIONS + " раз");
        System.out.println("Ожидаемый результат: " + (THREAD_COUNT * ITERATIONS));
        
        Thread[] threads = new Thread[THREAD_COUNT];
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    safeCounter.increment();
                }
            }, "SafeThread-" + (i + 1));
            threads[i].start();
        }
        
        // Ждем завершения всех потоков
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Фактический результат: " + safeCounter.getCount());
        System.out.println("✓ Результат ВЕРНЫЙ благодаря synchronized!");
        System.out.println("  Синхронизация гарантирует, что только один поток изменяет счетчик");
    }
    
    /**
     * Демонстрация контроля доступа к взлетно-посадочной полосе
     */
    private static void demonstrateRunwayControl() {
        RunwayController runway = new RunwayController("Runway-01");
        
        // Создаем самолеты
        String[] aircraft = {"Boeing-737", "Airbus-A320", "Boeing-777", "Airbus-A380"};
        String[] operations = {"taking off", "landing", "taking off", "landing"};
        
        Thread[] threads = new Thread[aircraft.length];
        
        for (int i = 0; i < aircraft.length; i++) {
            final String plane = aircraft[i];
            final String operation = operations[i];
            
            threads[i] = new Thread(() -> {
                runway.performOperation(plane, operation);
            }, plane + "-Thread");
            
            threads[i].start();
        }
        
        // Ждем завершения всех операций
        try {
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("\n✓ Все операции на полосе завершены безопасно");
            System.out.println("  Синхронизация предотвратила одновременное использование полосы");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Демонстрация паттерна Producer-Consumer
     */
    private static void demonstrateProducerConsumer() {
        FuelTank tank = new FuelTank("Main-Tank", 10000);
        
        // Производители (поставщики топлива)
        Thread supplier1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    Thread.sleep(800);
                    tank.refill(3000, "Supplier-1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Supplier-1-Thread");
        
        Thread supplier2 = new Thread(() -> {
            try {
                for (int i = 1; i <= 2; i++) {
                    Thread.sleep(1200);
                    tank.refill(4000, "Supplier-2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Supplier-2-Thread");
        
        // Потребители (самолеты)
        Thread aircraft1 = new Thread(() -> {
            try {
                Thread.sleep(500);
                tank.withdraw(2500, "Boeing-737");
                Thread.sleep(1000);
                tank.withdraw(2500, "Boeing-737");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Boeing-737-Thread");
        
        Thread aircraft2 = new Thread(() -> {
            try {
                Thread.sleep(700);
                tank.withdraw(3500, "Airbus-A320");
                Thread.sleep(1200);
                tank.withdraw(3000, "Airbus-A320");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Airbus-A320-Thread");
        
        // Запускаем всех
        supplier1.start();
        supplier2.start();
        aircraft1.start();
        aircraft2.start();
        
        try {
            supplier1.join();
            supplier2.join();
            aircraft1.join();
            aircraft2.join();
            
            System.out.println("\n✓ Все операции с топливом завершены");
            System.out.println("  Финальный уровень в резервуаре: " + 
                             String.format("%.0f", tank.getCurrentLevel()) + " литров");
            System.out.println("  wait/notify обеспечили корректную координацию");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Комплексная демонстрация синхронизации
     */
    private static void demonstrateComplexSynchronization() {
        RunwayController runway = new RunwayController("Runway-02");
        FuelTank tank = new FuelTank("Airport-Tank", 15000);
        
        // Заправляем резервуар достаточным количеством топлива
        Thread supplier = new Thread(() -> {
            try {
                tank.refill(13000, "Initial-Supply");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Supplier-Thread");
        supplier.start();
        
        try {
            supplier.join();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Создаем самолеты с полным циклом операций
        Thread flight1 = new Thread(() -> {
            try {
                // Заправка
                tank.withdraw(4000, "Flight-AA101");
                Thread.sleep(300);
                // Взлет
                runway.performOperation("Flight-AA101", "taking off");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Flight-AA101");
        
        Thread flight2 = new Thread(() -> {
            try {
                // Посадка
                runway.performOperation("Flight-BA202", "landing");
                Thread.sleep(300);
                // Заправка
                tank.withdraw(3500, "Flight-BA202");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Flight-BA202");
        
        Thread flight3 = new Thread(() -> {
            try {
                Thread.sleep(200);
                // Заправка
                tank.withdraw(4500, "Flight-LH303");
                Thread.sleep(300);
                // Взлет
                runway.performOperation("Flight-LH303", "taking off");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Flight-LH303");
        
        System.out.println("Запуск комплексных операций...\n");
        
        flight1.start();
        flight2.start();
        flight3.start();
        
        try {
            flight1.join();
            flight2.join();
            flight3.join();
            
            System.out.println("\n✓ Все комплексные операции завершены успешно");
            System.out.println("  Синхронизация обеспечила безопасную координацию:");
            System.out.println("  - Взлетно-посадочная полоса использовалась по очереди");
            System.out.println("  - Топливо распределялось корректно");
            System.out.println("  - Не было конфликтов между потоками");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
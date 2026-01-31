import common.Aeroplane;
import common.Engine;

/**
 * Демонстрация работы с приоритетами потоков
 * 
 * Задание 3: Назначение приоритетов потокам
 * 
 * Ключевые концепции:
 * 1. Установка приоритетов через setPriority()
 * 2. Использование констант MIN_PRIORITY, NORM_PRIORITY, MAX_PRIORITY
 * 3. Влияние приоритетов на порядок выполнения
 * 4. Практические сценарии использования приоритетов
 */
public class PriorityDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ЗАДАНИЕ 3: ДЕМОНСТРАЦИЯ РАБОТЫ С ПРИОРИТЕТАМИ ПОТОКОВ");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // Информация о приоритетах
        System.out.println("Диапазон приоритетов в Java:");
        System.out.println("  MIN_PRIORITY  = " + Thread.MIN_PRIORITY + " (минимальный)");
        System.out.println("  NORM_PRIORITY = " + Thread.NORM_PRIORITY + " (по умолчанию)");
        System.out.println("  MAX_PRIORITY  = " + Thread.MAX_PRIORITY + " (максимальный)");
        System.out.println();
        
        // Создаем самолеты
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
        
        // ========================================================================
        // ПРИМЕР 1: Базовая установка приоритетов
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 1: Базовая установка приоритетов");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: создание потоков с разными приоритетами\n");
        
        // Создаем задачи
        EmergencyLandingTask emergency = new EmergencyLandingTask(boeing737, "Engine failure");
        RoutineMaintenanceTask maintenance = new RoutineMaintenanceTask(airbusA320, "Scheduled check");
        DataLoggingTask logging = new DataLoggingTask(boeing777, 5);
        
        // Создаем потоки
        Thread emergencyThread = new Thread(emergency, "Emergency-Thread");
        Thread maintenanceThread = new Thread(maintenance, "Maintenance-Thread");
        Thread loggingThread = new Thread(logging, "Logging-Thread");
        
        // Устанавливаем приоритеты
        emergencyThread.setPriority(Thread.MAX_PRIORITY);    // 10 - критическая задача
        maintenanceThread.setPriority(Thread.NORM_PRIORITY); // 5 - обычная задача
        loggingThread.setPriority(Thread.MIN_PRIORITY);      // 1 - фоновая задача
        
        System.out.println("Приоритеты установлены:");
        System.out.println("  " + emergencyThread.getName() + ": " + emergencyThread.getPriority());
        System.out.println("  " + maintenanceThread.getName() + ": " + maintenanceThread.getPriority());
        System.out.println("  " + loggingThread.getName() + ": " + loggingThread.getPriority());
        System.out.println();
        
        // Запускаем потоки
        emergencyThread.start();
        maintenanceThread.start();
        loggingThread.start();
        
        try {
            emergencyThread.join();
            maintenanceThread.join();
            loggingThread.join();
            System.out.println("\n✓ Все задачи завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 2: Сравнение времени выполнения
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 2: Влияние приоритетов на время выполнения");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: запуск одинаковых задач с разными приоритетами\n");
        
        // Создаем три одинаковые задачи
        DataLoggingTask highPriorityTask = new DataLoggingTask(boeing737, 8);
        DataLoggingTask normalPriorityTask = new DataLoggingTask(airbusA320, 8);
        DataLoggingTask lowPriorityTask = new DataLoggingTask(boeing777, 8);
        
        Thread highThread = new Thread(highPriorityTask, "High-Priority");
        Thread normalThread = new Thread(normalPriorityTask, "Normal-Priority");
        Thread lowThread = new Thread(lowPriorityTask, "Low-Priority");
        
        highThread.setPriority(Thread.MAX_PRIORITY);
        normalThread.setPriority(Thread.NORM_PRIORITY);
        lowThread.setPriority(Thread.MIN_PRIORITY);
        
        System.out.println("Запуск трех одинаковых задач с разными приоритетами...\n");
        
        // Запускаем одновременно
        highThread.start();
        normalThread.start();
        lowThread.start();
        
        try {
            highThread.join();
            normalThread.join();
            lowThread.join();
            
            System.out.println("\n--- Результаты ---");
            System.out.println("High Priority (10):   " + highPriorityTask.getDuration() + "ms");
            System.out.println("Normal Priority (5):  " + normalPriorityTask.getDuration() + "ms");
            System.out.println("Low Priority (1):     " + lowPriorityTask.getDuration() + "ms");
            System.out.println("\nПримечание: Разница может быть незначительной из-за особенностей планировщика ОС");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 3: Изменение приоритета во время выполнения
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 3: Динамическое изменение приоритета");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: изменение приоритета работающего потока\n");
        
        DataLoggingTask dynamicTask = new DataLoggingTask(boeing737, 6);
        Thread dynamicThread = new Thread(dynamicTask, "Dynamic-Priority");
        
        System.out.println("Начальный приоритет: " + dynamicThread.getPriority());
        dynamicThread.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Установлен MIN_PRIORITY: " + dynamicThread.getPriority());
        
        dynamicThread.start();
        
        try {
            Thread.sleep(1000); // Даем потоку поработать
            
            System.out.println("\n>>> Повышаем приоритет до MAX_PRIORITY");
            dynamicThread.setPriority(Thread.MAX_PRIORITY);
            System.out.println("Новый приоритет: " + dynamicThread.getPriority() + "\n");
            
            dynamicThread.join();
            System.out.println("\n✓ Задача завершена");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 4: Пользовательские приоритеты
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 4: Использование пользовательских значений приоритетов");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: установка произвольных значений от 1 до 10\n");
        
        // Создаем потоки с разными приоритетами
        Thread[] threads = new Thread[5];
        int[] priorities = {1, 3, 5, 7, 10};
        
        for (int i = 0; i < 5; i++) {
            final int taskNum = i + 1;
            threads[i] = new Thread(() -> {
                String name = Thread.currentThread().getName();
                int priority = Thread.currentThread().getPriority();
                System.out.println("[" + name + "] Running with priority " + priority);
                try {
                    Thread.sleep(300);
                    System.out.println("[" + name + "] Completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Task-" + taskNum);
            
            threads[i].setPriority(priorities[i]);
            System.out.println("Created " + threads[i].getName() + " with priority " + priorities[i]);
        }
        
        System.out.println("\nЗапуск всех потоков...\n");
        
        for (Thread thread : threads) {
            thread.start();
        }
        
        try {
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("\n✓ Все задачи завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 5: Практический сценарий - Управление воздушным движением
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 5: Практический сценарий - Управление воздушным движением");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: реалистичное использование приоритетов\n");
        
        // Критическая ситуация
        EmergencyLandingTask emergency1 = new EmergencyLandingTask(
            boeing737, "Medical emergency"
        );
        
        // Обычные операции
        RoutineMaintenanceTask routine1 = new RoutineMaintenanceTask(
            airbusA320, "Pre-flight inspection"
        );
        
        RefuelingTask refuel = new RefuelingTask(boeing777, 8000);
        
        // Фоновые задачи
        DataLoggingTask log1 = new DataLoggingTask(boeing737, 4);
        DataLoggingTask log2 = new DataLoggingTask(airbusA320, 4);
        
        // Создаем потоки
        Thread emergThread = new Thread(emergency1, "EMERGENCY");
        Thread routineThread = new Thread(routine1, "Routine-Ops");
        Thread refuelThread = new Thread(refuel, "Refuel-Ops");
        Thread log1Thread = new Thread(log1, "Background-Log-1");
        Thread log2Thread = new Thread(log2, "Background-Log-2");
        
        // Устанавливаем приоритеты согласно важности
        emergThread.setPriority(Thread.MAX_PRIORITY);      // 10 - критично!
        routineThread.setPriority(Thread.NORM_PRIORITY);   // 5 - обычная работа
        refuelThread.setPriority(Thread.NORM_PRIORITY);    // 5 - обычная работа
        log1Thread.setPriority(Thread.MIN_PRIORITY);       // 1 - фон
        log2Thread.setPriority(Thread.MIN_PRIORITY);       // 1 - фон
        
        System.out.println("Приоритеты операций:");
        System.out.println("  🚨 " + emergThread.getName() + ": " + emergThread.getPriority() + " (КРИТИЧНО)");
        System.out.println("  🔧 " + routineThread.getName() + ": " + routineThread.getPriority() + " (обычная)");
        System.out.println("  ⛽ " + refuelThread.getName() + ": " + refuelThread.getPriority() + " (обычная)");
        System.out.println("  📝 " + log1Thread.getName() + ": " + log1Thread.getPriority() + " (фон)");
        System.out.println("  📝 " + log2Thread.getName() + ": " + log2Thread.getPriority() + " (фон)");
        System.out.println();
        
        // Запускаем все операции
        emergThread.start();
        routineThread.start();
        refuelThread.start();
        log1Thread.start();
        log2Thread.start();
        
        try {
            emergThread.join();
            routineThread.join();
            refuelThread.join();
            log1Thread.join();
            log2Thread.join();
            System.out.println("\n✓ Все операции завершены");
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
        System.out.println("✓ 1. Установка приоритетов через setPriority()");
        System.out.println("✓ 2. Использование констант MIN/NORM/MAX_PRIORITY");
        System.out.println("✓ 3. Пользовательские значения приоритетов (1-10)");
        System.out.println("✓ 4. Динамическое изменение приоритета");
        System.out.println("✓ 5. Влияние приоритетов на выполнение");
        System.out.println("✓ 6. Практические сценарии использования");
        
        System.out.println("\nГлавный поток (main) завершен!");
        System.out.println("=".repeat(80));
    }
}
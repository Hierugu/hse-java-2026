import common.Aeroplane;
import common.Engine;

/**
 * Демонстрация работы с потоками через интерфейс Runnable
 * 
 * Задание 2: Создание потоков с использованием интерфейса Runnable
 * 
 * Ключевые концепции:
 * 1. Реализация интерфейса Runnable
 * 2. Создание потоков через new Thread(Runnable)
 * 3. Повторное использование задач в разных потоках
 * 4. Использование лямбда-выражений
 * 5. Сравнение с подходом через наследование Thread
 */
public class RunnableDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ЗАДАНИЕ 2: ДЕМОНСТРАЦИЯ РАБОТЫ С ИНТЕРФЕЙСОМ RUNNABLE");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // Создаем самолеты для демонстрации
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
        
        System.out.println("Создано 3 самолета для демонстрации работы с Runnable\n");
        
        // ========================================================================
        // ПРИМЕР 1: Базовое использование Runnable
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 1: Базовое использование Runnable");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: создание задач через реализацию Runnable");
        System.out.println("Запускаем задачи заправки и посадки параллельно\n");
        
        // Создаем задачи
        RefuelingTask refueling1 = new RefuelingTask(boeing737, 5000);
        BoardingTask boarding1 = new BoardingTask(airbusA320, 180);
        
        // Создаем потоки, передавая задачи
        Thread refuelThread1 = new Thread(refueling1, "Refuel-Thread-1");
        Thread boardThread1 = new Thread(boarding1, "Board-Thread-1");
        
        // Запускаем потоки
        refuelThread1.start();
        boardThread1.start();
        
        // Ждем завершения
        try {
            refuelThread1.join();
            boardThread1.join();
            System.out.println("\n✓ Обе задачи завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 2: Повторное использование одной задачи в разных потоках
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 2: Повторное использование задачи");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: одна задача может быть запущена в нескольких потоках");
        System.out.println("Запускаем одну задачу предполетной проверки в 3 потоках\n");
        
        // Создаем одну задачу
        PreFlightCheckTask checkTask = new PreFlightCheckTask(boeing777, "Chief-Inspector");
        
        // Создаем несколько потоков с одной задачей
        Thread checkThread1 = new Thread(checkTask, "Check-Thread-1");
        Thread checkThread2 = new Thread(checkTask, "Check-Thread-2");
        Thread checkThread3 = new Thread(checkTask, "Check-Thread-3");
        
        // Запускаем все потоки
        checkThread1.start();
        checkThread2.start();
        checkThread3.start();
        
        try {
            checkThread1.join();
            checkThread2.join();
            checkThread3.join();
            System.out.println("\n✓ Все проверки завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 3: Использование анонимного класса
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 3: Анонимный класс Runnable");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: создание Runnable через анонимный класс\n");
        
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("[" + threadName + "] Starting anonymous task for " + 
                                 boeing737.getModel());
                try {
                    for (int i = 1; i <= 3; i++) {
                        System.out.println("[" + threadName + "] Step " + i + " of 3");
                        Thread.sleep(500);
                    }
                    System.out.println("[" + threadName + "] ✓ Anonymous task completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Anonymous-Thread");
        
        anonymousThread.start();
        
        try {
            anonymousThread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 4: Использование лямбда-выражений (Java 8+)
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 4: Лямбда-выражения для Runnable");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: современный способ создания Runnable через лямбда\n");
        
        // Простая лямбда
        Thread lambdaThread1 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Lambda task started");
            try {
                Thread.sleep(500);
                System.out.println("[" + threadName + "] Processing " + airbusA320.getModel());
                Thread.sleep(500);
                System.out.println("[" + threadName + "] ✓ Lambda task completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Lambda-Thread-1");
        
        // Лямбда с захватом переменных
        String destination = "New York";
        Thread lambdaThread2 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Preparing flight to " + destination);
            try {
                Thread.sleep(400);
                System.out.println("[" + threadName + "] Flight plan for " + 
                                 boeing777.getModel() + " approved");
                Thread.sleep(400);
                System.out.println("[" + threadName + "] ✓ Ready for departure to " + destination);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Lambda-Thread-2");
        
        lambdaThread1.start();
        lambdaThread2.start();
        
        try {
            lambdaThread1.join();
            lambdaThread2.join();
            System.out.println("\n✓ Все лямбда-задачи завершены");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 5: Комплексная подготовка к полету
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 5: Комплексная подготовка к полету");
        System.out.println("=".repeat(80));
        System.out.println("Демонстрация: параллельное выполнение всех задач подготовки\n");
        
        // Создаем все задачи для одного самолета
        RefuelingTask refueling = new RefuelingTask(boeing737, 6000, "Station-A1");
        BoardingTask boarding = new BoardingTask(boeing737, 189, "Gate-B5");
        PreFlightCheckTask preCheck = new PreFlightCheckTask(boeing737, "Captain-Smith");
        
        // Создаем потоки
        Thread refuelThread = new Thread(refueling, "Refuel-B737");
        Thread boardThread = new Thread(boarding, "Board-B737");
        Thread checkThread = new Thread(preCheck, "Check-B737");
        
        // Запускаем все задачи параллельно
        System.out.println("Starting all preparation tasks for " + boeing737.getModel() + "...\n");
        refuelThread.start();
        boardThread.start();
        checkThread.start();
        
        // Ждем завершения всех задач
        try {
            refuelThread.join();
            boardThread.join();
            checkThread.join();
            System.out.println("\n✓ Все задачи подготовки завершены - самолет готов к вылету!");
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted");
            Thread.currentThread().interrupt();
        }
        
        // ========================================================================
        // ПРИМЕР 6: Сравнение Thread vs Runnable
        // ========================================================================
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ПРИМЕР 6: Сравнение подходов Thread vs Runnable");
        System.out.println("=".repeat(80));
        
        System.out.println("\nПодход 1: Наследование от Thread (из Задания 1)");
        PlaneMaintenanceThread threadApproach = new PlaneMaintenanceThread(airbusA320);
        threadApproach.start();
        
        System.out.println("\nПодход 2: Реализация Runnable (Задание 2)");
        RefuelingTask runnableApproach = new RefuelingTask(airbusA320, 4500);
        Thread runnableThread = new Thread(runnableApproach, "Runnable-Approach");
        runnableThread.start();
        
        try {
            threadApproach.join();
            runnableThread.join();
            System.out.println("\n✓ Оба подхода завершены");
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
        System.out.println("✓ 1. Реализация интерфейса Runnable");
        System.out.println("✓ 2. Создание потоков через new Thread(Runnable)");
        System.out.println("✓ 3. Повторное использование одной задачи в разных потоках");
        System.out.println("✓ 4. Анонимные классы для Runnable");
        System.out.println("✓ 5. Лямбда-выражения (современный подход)");
        System.out.println("✓ 6. Параллельное выполнение множества задач");
        System.out.println("✓ 7. Сравнение Thread vs Runnable");
        
        System.out.println("\nГлавный поток (main) завершен!");
        System.out.println("=".repeat(80));
    }
}
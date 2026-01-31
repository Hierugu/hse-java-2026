import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=".repeat(80));
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА №3: МНОГОПОТОЧНОСТЬ В JAVA");
        System.out.println("=".repeat(80));
        
        waitForUser(scanner, "\nНажмите Enter для запуска Задания 1 (Thread)...");
        runDemo1();
        
        waitForUser(scanner, "\nНажмите Enter для запуска Задания 2 (Runnable)...");
        runDemo2();
        
        waitForUser(scanner, "\nНажмите Enter для запуска Задания 3 (Приоритеты)...");
        runDemo3();
        
        waitForUser(scanner, "\nНажмите Enter для запуска Задания 4 (Синхронизация)...");
        runDemo4();
        
        scanner.close();
    }
    
    private static void waitForUser(Scanner scanner, String message) {
        System.out.println(message);
        scanner.nextLine();
    }
    
    private static void runDemo1() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ЗАПУСК ЗАДАНИЯ 1: КЛАСС THREAD");
        System.out.println("=".repeat(80));
        System.out.println();
        
        try {
            ThreadDemo.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении ThreadDemo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void runDemo2() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ЗАПУСК ЗАДАНИЯ 2: ИНТЕРФЕЙС RUNNABLE");
        System.out.println("=".repeat(80));
        System.out.println();
        
        try {
            RunnableDemo.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении RunnableDemo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void runDemo3() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ЗАПУСК ЗАДАНИЯ 3: ПРИОРИТЕТЫ ПОТОКОВ");
        System.out.println("=".repeat(80));
        System.out.println();
        
        try {
            PriorityDemo.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении PriorityDemo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void runDemo4() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ЗАПУСК ЗАДАНИЯ 4: СИНХРОНИЗАЦИЯ ПОТОКОВ");
        System.out.println("=".repeat(80));
        System.out.println();
        
        try {
            SynchronizationDemo.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка при выполнении SynchronizationDemo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
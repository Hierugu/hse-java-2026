// Главный класс для демонстрации обеих задач ЛР4
public class App {
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  Лабораторная работа 4                     ║");
        System.out.println("║        Пакет java.net. Сетевые приложения Java             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Задание 1: URL Parser
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                    ЗАДАНИЕ 1                              ");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        URLParser.main(args);
        
        // Пауза между заданиями
        pause(2000);
        
        // Задание 2: Multi-threaded Web Content Fetcher
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("                    ЗАДАНИЕ 2                              ");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        WebContentFetcher.main(args);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              Все задания выполнены успешно!                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
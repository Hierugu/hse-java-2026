// Task 2: Многопоточное приложение для загрузки содержимого веб-сайтов
public class WebContentFetcher {
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      Task 2: Multi-threaded Web Content Fetcher           ║");
        System.out.println("║      Concurrent website content downloading                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Создаем читателей для разных сайтов
        WebsiteReader reader1 = new WebsiteReader("http://www.example.com", "Thread-1");
        WebsiteReader reader2 = new WebsiteReader("http://www.example.org", "Thread-2");
        
        // Создаем потоки
        Thread thread1 = new Thread(reader1);
        Thread thread2 = new Thread(reader2);
        
        System.out.println("Starting concurrent website fetching...\n");
        
        long startTime = System.currentTimeMillis();
        
        // Запускаем потоки одновременно
        thread1.start();
        thread2.start();
        
        // Ожидаем завершения обоих потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }
        
        long totalTime = System.currentTimeMillis() - startTime;
        
        // Выводим итоговую статистику
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SUMMARY:");
        System.out.println("=".repeat(60));
        System.out.println("Thread-1 status: " + (reader1.isSuccess() ? "SUCCESS" : "FAILED"));
        System.out.println("Thread-2 status: " + (reader2.isSuccess() ? "SUCCESS" : "FAILED"));
        System.out.println("\nTotal execution time: " + (totalTime / 1000.0) + " seconds");
        
        if (reader1.isSuccess() && reader2.isSuccess()) {
            int totalLines = reader1.getLinesRead() + reader2.getLinesRead();
            int totalChars = reader1.getCharsRead() + reader2.getCharsRead();
            System.out.println("Total lines read: " + totalLines);
            System.out.println("Total characters read: " + totalChars);
        }
        
        System.out.println("=".repeat(60) + "\n");
        
        // Дополнительная демонстрация с большим количеством потоков
        demonstrateMultipleThreads();
    }
    
    private static void demonstrateMultipleThreads() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      Extended Demo: Multiple Concurrent Threads           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        String[] urls = {
            "http://www.example.com",
            "http://www.example.org",
            "http://www.example.net"
        };
        
        Thread[] threads = new Thread[urls.length];
        WebsiteReader[] readers = new WebsiteReader[urls.length];
        
        // Создаем и запускаем потоки
        for (int i = 0; i < urls.length; i++) {
            readers[i] = new WebsiteReader(urls[i], "Thread-" + (i + 1));
            threads[i] = new Thread(readers[i]);
            threads[i].start();
        }
        
        // Ожидаем завершения всех потоков
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("All threads completed!");
        System.out.println("=".repeat(60) + "\n");
    }
}
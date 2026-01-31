import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

// Runnable для чтения содержимого веб-сайта в отдельном потоке
public class WebsiteReader implements Runnable {
    private final String urlString;
    private final String threadName;
    private int linesRead = 0;
    private int charsRead = 0;
    private long executionTime = 0;
    private boolean success = false;
    
    public WebsiteReader(String urlString, String threadName) {
        this.urlString = urlString;
        this.threadName = threadName;
    }
    
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        
        synchronized (System.out) {
            System.out.println("[" + threadName + "] Starting: " + urlString);
        }
        
        try {
            URL url = new URL(urlString);
            
            // Открываем поток для чтения
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                
                synchronized (System.out) {
                    System.out.println("[" + threadName + "] Connected successfully");
                }
                
                String line;
                while ((line = reader.readLine()) != null) {
                    linesRead++;
                    charsRead += line.length();
                    
                    // Имитация обработки данных
                    Thread.sleep(10);
                }
                
                success = true;
                
            }
            
        } catch (UnknownHostException e) {
            synchronized (System.err) {
                System.err.println("[" + threadName + "] ERROR: Unknown host - " + e.getMessage());
            }
        } catch (IOException e) {
            synchronized (System.err) {
                System.err.println("[" + threadName + "] ERROR: I/O error - " + e.getMessage());
            }
        } catch (InterruptedException e) {
            synchronized (System.err) {
                System.err.println("[" + threadName + "] ERROR: Thread interrupted");
            }
        }
        
        executionTime = System.currentTimeMillis() - startTime;
        
        synchronized (System.out) {
            if (success) {
                System.out.println("[" + threadName + "] Completed: " + linesRead + 
                                 " lines, " + charsRead + " characters");
                System.out.println("[" + threadName + "] Execution time: " + 
                                 (executionTime / 1000.0) + " seconds");
            } else {
                System.out.println("[" + threadName + "] Failed to fetch content");
            }
        }
    }
    
    public int getLinesRead() {
        return linesRead;
    }
    
    public int getCharsRead() {
        return charsRead;
    }
    
    public long getExecutionTime() {
        return executionTime;
    }
    
    public boolean isSuccess() {
        return success;
    }
}
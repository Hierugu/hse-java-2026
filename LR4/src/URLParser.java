import java.net.MalformedURLException;
import java.net.URL;

// Task 1: Парсинг компонентов URL
public class URLParser {
    
    public static void parseURL(String urlString) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Parsing URL: " + urlString);
        System.out.println("=".repeat(60));
        
        try {
            URL url = new URL(urlString);
            displayURLComponents(url);
        } catch (MalformedURLException e) {
            System.err.println("ERROR: Invalid URL format!");
            System.err.println("Reason: " + e.getMessage());
        }
    }
    
    public static void displayURLComponents(URL url) {
        System.out.println("\n📋 URL Components:");
        System.out.println("-".repeat(60));
        
        String protocol = url.getProtocol();
        System.out.printf("%-20s: %s\n", "Protocol", protocol);
        
        String host = url.getHost();
        System.out.printf("%-20s: %s\n", "Host", host);
        
        int port = url.getPort();
        int defaultPort = url.getDefaultPort();
        if (port == -1) {
            System.out.printf("%-20s: %d (default for %s)\n", "Port", defaultPort, protocol);
        } else {
            System.out.printf("%-20s: %d (explicit)\n", "Port", port);
            System.out.printf("%-20s: %d\n", "Default Port", defaultPort);
        }
        
        String path = url.getPath();
        System.out.printf("%-20s: %s\n", "Path", path.isEmpty() ? "/" : path);
        
        String file = url.getFile();
        System.out.printf("%-20s: %s\n", "File", file.isEmpty() ? "/" : file);
        
        String query = url.getQuery();
        if (query != null && !query.isEmpty()) {
            System.out.printf("%-20s: %s\n", "Query Parameters", query);
        }
        
        String ref = url.getRef();
        if (ref != null && !ref.isEmpty()) {
            System.out.printf("%-20s: %s\n", "Fragment/Anchor", ref);
        }
        
        String authority = url.getAuthority();
        System.out.printf("%-20s: %s\n", "Authority", authority);
        
        System.out.println("-".repeat(60));
        System.out.printf("%-20s: %s\n", "Complete URL", url.toString());
    }
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         Task 1: URL Component Parser Demo                 ║");
        System.out.println("║         Java Networking (java.net.URL)                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        parseURL("http://www.example.com");
        parseURL("https://www.example.com:8080/api/users");
        parseURL("http://www.example.com/search?q=java&lang=en");
        parseURL("ftp://ftp.example.com/pub/docs/manual.pdf");
        parseURL("http://localhost:3000/index.html");
        parseURL("https://docs.oracle.com/javase/tutorial/networking/urls/index.html#DOWNLOADING");
        parseURL("https://user:pass@www.example.com:8443/path/to/resource.html?id=123&type=test#section2");
        
        // Демонстрация обработки ошибок
        parseURL("htp://invalid url with spaces");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("URL parsing demonstration completed!");
        System.out.println("=".repeat(60) + "\n");
    }
}
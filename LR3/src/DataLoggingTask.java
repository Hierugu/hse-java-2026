import common.Aeroplane;

public class DataLoggingTask implements Runnable {
    private Aeroplane plane;
    private int recordCount;
    private long startTime;
    private long endTime;
    
    public DataLoggingTask(Aeroplane plane, int recordCount) {
        this.plane = plane;
        this.recordCount = recordCount;
    }
    
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        
        System.out.println("[" + threadName + "] Starting background logging. Priority: " + priority);
        System.out.println("[" + threadName + "] " + plane.getModel() + " - Logging " + recordCount + " records");
        
        try {
            for (int i = 1; i <= recordCount; i++) {
                System.out.println("[" + threadName + "] 📝 Logging record " + i + "/" + recordCount + 
                                 " for " + plane.getModel());
                
                Thread.sleep(200);
                
                if (i % 3 == 0) {
                    System.out.println("[" + threadName + "] 📝 Compressing data...");
                    Thread.sleep(100);
                }
            }
            
            System.out.println("[" + threadName + "] 📝 Finalizing log file...");
            Thread.sleep(150);
            
            endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("[" + threadName + "] ✓ Logging completed in " + duration + "ms");
            System.out.println("[" + threadName + "] Total records: " + recordCount);
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Logging interrupted!");
            Thread.currentThread().interrupt();
        }
    }
    
    public long getDuration() {
        return endTime - startTime;
    }
}
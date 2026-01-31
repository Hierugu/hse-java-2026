import common.Aeroplane;

public class EmergencyLandingTask implements Runnable {
    private Aeroplane plane;
    private String emergencyType;
    private long startTime;
    private long endTime;
    
    public EmergencyLandingTask(Aeroplane plane, String emergencyType) {
        this.plane = plane;
        this.emergencyType = emergencyType;
    }
    
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        
        System.out.println("[" + threadName + "] EMERGENCY! Priority: " + priority);
        System.out.println("[" + threadName + "] " + plane.getModel() + " - " + emergencyType);
        
        try {
            System.out.println("[" + threadName + "] ⚠️  Declaring emergency...");
            Thread.sleep(100);
            
            System.out.println("[" + threadName + "] ⚠️  Clearing airspace...");
            Thread.sleep(100);
            
            System.out.println("[" + threadName + "] ⚠️  Preparing emergency landing...");
            Thread.sleep(150);
            
            System.out.println("[" + threadName + "] ⚠️  Emergency services on standby...");
            Thread.sleep(100);
            
            System.out.println("[" + threadName + "] ⚠️  Beginning emergency descent...");
            Thread.sleep(200);
            
            System.out.println("[" + threadName + "] ⚠️  Final approach...");
            Thread.sleep(150);
            
            System.out.println("[" + threadName + "] ⚠️  EMERGENCY LANDING COMPLETE!");
            
            endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("[" + threadName + "] ✓ Emergency handled in " + duration + "ms");
            System.out.println("[" + threadName + "] Aircraft and passengers safe!");
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Emergency procedure interrupted!");
            Thread.currentThread().interrupt();
        }
    }
    
    public long getDuration() {
        return endTime - startTime;
    }
}
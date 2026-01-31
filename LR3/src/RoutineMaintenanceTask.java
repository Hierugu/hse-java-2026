import common.Aeroplane;

public class RoutineMaintenanceTask implements Runnable {
    private Aeroplane plane;
    private String maintenanceType;
    private long startTime;
    private long endTime;
    
    public RoutineMaintenanceTask(Aeroplane plane, String maintenanceType) {
        this.plane = plane;
        this.maintenanceType = maintenanceType;
    }
    
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        
        System.out.println("[" + threadName + "] Starting routine maintenance. Priority: " + priority);
        System.out.println("[" + threadName + "] " + plane.getModel() + " - " + maintenanceType);
        
        try {
            System.out.println("[" + threadName + "] 🔧 Checking systems...");
            Thread.sleep(200);
            
            System.out.println("[" + threadName + "] 🔧 Inspecting components...");
            Thread.sleep(250);
            
            System.out.println("[" + threadName + "] 🔧 Testing equipment...");
            Thread.sleep(200);
            
            System.out.println("[" + threadName + "] 🔧 Replacing filters...");
            Thread.sleep(180);
            
            System.out.println("[" + threadName + "] 🔧 Lubricating parts...");
            Thread.sleep(150);
            
            System.out.println("[" + threadName + "] 🔧 Final inspection...");
            Thread.sleep(200);
            
            System.out.println("[" + threadName + "] 🔧 Updating maintenance log...");
            Thread.sleep(100);
            
            endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("[" + threadName + "] ✓ Routine maintenance completed in " + duration + "ms");
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Maintenance interrupted!");
            Thread.currentThread().interrupt();
        }
    }
    
    public long getDuration() {
        return endTime - startTime;
    }
}
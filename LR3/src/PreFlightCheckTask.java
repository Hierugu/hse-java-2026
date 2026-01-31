import common.Aeroplane;

public class PreFlightCheckTask implements Runnable {
    private Aeroplane plane;
    private String inspector;
    
    public PreFlightCheckTask(Aeroplane plane) {
        this.plane = plane;
        this.inspector = "Inspector-" + (int)(Math.random() * 100);
    }
    
    public PreFlightCheckTask(Aeroplane plane, String inspector) {
        this.plane = plane;
        this.inspector = inspector;
    }
    
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Starting pre-flight check for " +
                         plane.getModel() + " by " + inspector);
        
        try {
            System.out.println("[" + threadName + "] External inspection of " + plane.getModel());
            Thread.sleep(600);
            System.out.println("[" + threadName + "] - Checking fuselage and wings");
            Thread.sleep(400);
            System.out.println("[" + threadName + "] - Inspecting landing gear");
            Thread.sleep(400);
            
            System.out.println("[" + threadName + "] Engine inspection");
            Thread.sleep(500);
            System.out.println("[" + threadName + "] - Testing engine systems");
            Thread.sleep(400);
            
            System.out.println("[" + threadName + "] Avionics check");
            Thread.sleep(500);
            System.out.println("[" + threadName + "] - Testing navigation systems");
            Thread.sleep(300);
            System.out.println("[" + threadName + "] - Checking communication equipment");
            Thread.sleep(300);
            
            System.out.println("[" + threadName + "] Fuel system verification");
            Thread.sleep(400);
            
            System.out.println("[" + threadName + "] Hydraulic systems check");
            Thread.sleep(400);
            
            System.out.println("[" + threadName + "] Final safety checks");
            Thread.sleep(500);
            
            System.out.println("[" + threadName + "] ✓ Pre-flight check completed for " + 
                             plane.getModel() + " - CLEARED FOR TAKEOFF");
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Pre-flight check interrupted for " + 
                             plane.getModel());
            Thread.currentThread().interrupt();
        }
    }
}
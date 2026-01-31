public class RunwayController {
    private String runwayName;
    private boolean isOccupied;
    private String currentUser;
    
    public RunwayController(String runwayName) {
        this.runwayName = runwayName;
        this.isOccupied = false;
        this.currentUser = null;
    }
    
    public synchronized void requestRunway(String aircraftId, String operation) {
        String threadName = Thread.currentThread().getName();
        
        System.out.println("[" + threadName + "] " + aircraftId + 
                         " requesting " + runwayName + " for " + operation);
        
        while (isOccupied) {
            try {
                System.out.println("[" + threadName + "] " + aircraftId +
                                 " waiting... (occupied by " + currentUser + ")");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        isOccupied = true;
        currentUser = aircraftId;
        System.out.println("[" + threadName + "] ✓ " + aircraftId + 
                         " acquired " + runwayName + " for " + operation);
    }
    
    public synchronized void useRunway(String aircraftId, String operation) {
        String threadName = Thread.currentThread().getName();
        
        try {
            System.out.println("[" + threadName + "] >>> " + aircraftId + 
                             " is " + operation + " on " + runwayName);
            
            if (operation.contains("taking off")) {
                Thread.sleep(1500);
            } else if (operation.contains("landing")) {
                Thread.sleep(1200);
            } else {
                Thread.sleep(1000);
            }
            
            System.out.println("[" + threadName + "] <<< " + aircraftId + 
                             " completed " + operation);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public synchronized void releaseRunway(String aircraftId) {
        String threadName = Thread.currentThread().getName();
        
        if (currentUser != null && currentUser.equals(aircraftId)) {
            isOccupied = false;
            currentUser = null;
            System.out.println("[" + threadName + "] ✓ " + aircraftId +
                             " released " + runwayName);
            notifyAll();
        }
    }
    
    public void performOperation(String aircraftId, String operation) {
        requestRunway(aircraftId, operation);
        useRunway(aircraftId, operation);
        releaseRunway(aircraftId);
    }
    
    public String getRunwayName() {
        return runwayName;
    }
    
    public synchronized boolean isAvailable() {
        return !isOccupied;
    }
}
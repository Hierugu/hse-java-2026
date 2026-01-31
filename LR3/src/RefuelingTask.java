import common.Aeroplane;

public class RefuelingTask implements Runnable {
    private Aeroplane plane;
    private double fuelAmount;
    private String station;
    
    public RefuelingTask(Aeroplane plane, double fuelAmount) {
        this.plane = plane;
        this.fuelAmount = fuelAmount;
        this.station = "Station-" + (int)(Math.random() * 100);
    }
    
    public RefuelingTask(Aeroplane plane, double fuelAmount, String station) {
        this.plane = plane;
        this.fuelAmount = fuelAmount;
        this.station = station;
    }
    
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Starting refueling for " + plane.getModel() +
                         " at " + station);
        
        try {
            System.out.println("[" + threadName + "] Connecting fuel hose...");
            Thread.sleep(500);
            
            System.out.println("[" + threadName + "] Checking fuel system...");
            Thread.sleep(300);
            
            double refueled = 0;
            while (refueled < fuelAmount) {
                double portion = Math.min(1000, fuelAmount - refueled);
                refueled += portion;
                System.out.println("[" + threadName + "] Refueling " + plane.getModel() + 
                                 ": " + String.format("%.0f", refueled) + "/" + 
                                 String.format("%.0f", fuelAmount) + " liters");
                Thread.sleep(400);
            }
            
            System.out.println("[" + threadName + "] Disconnecting fuel hose...");
            Thread.sleep(300);
            
            System.out.println("[" + threadName + "] ✓ Refueling completed for " + 
                             plane.getModel() + " (" + fuelAmount + " liters)");
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Refueling interrupted for " + 
                             plane.getModel());
            Thread.currentThread().interrupt();
        }
    }
}
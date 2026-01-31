import common.Aeroplane;

public class BoardingTask implements Runnable {
    private Aeroplane plane;
    private int passengerCount;
    private String gate;
    
    public BoardingTask(Aeroplane plane, int passengerCount) {
        this.plane = plane;
        this.passengerCount = passengerCount;
        this.gate = "Gate-" + (char)('A' + (int)(Math.random() * 10));
    }
    
    public BoardingTask(Aeroplane plane, int passengerCount, String gate) {
        this.plane = plane;
        this.passengerCount = passengerCount;
        this.gate = gate;
    }
    
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Starting boarding for " + plane.getModel() +
                         " at " + gate);
        
        try {
            System.out.println("[" + threadName + "] Opening boarding gate...");
            Thread.sleep(400);
            
            int boarded = 0;
            int groupSize = 30;
            
            while (boarded < passengerCount) {
                int currentGroup = Math.min(groupSize, passengerCount - boarded);
                boarded += currentGroup;
                
                System.out.println("[" + threadName + "] Boarding group: " + boarded + "/" + 
                                 passengerCount + " passengers on " + plane.getModel());
                Thread.sleep(600);
            }
            
            System.out.println("[" + threadName + "] Checking cabin, securing doors...");
            Thread.sleep(500);
            
            System.out.println("[" + threadName + "] Safety briefing in progress...");
            Thread.sleep(400);
            
            System.out.println("[" + threadName + "] ✓ Boarding completed for " + 
                             plane.getModel() + " (" + passengerCount + " passengers)");
            
        } catch (InterruptedException e) {
            System.out.println("[" + threadName + "] Boarding interrupted for " + 
                             plane.getModel());
            Thread.currentThread().interrupt();
        }
    }
}
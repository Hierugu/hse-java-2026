public class FuelTank {
    private double capacity;        // Максимальная вместимость
    private double currentLevel;    // Текущий уровень топлива
    private String tankId;
    
    public FuelTank(String tankId, double capacity) {
        this.tankId = tankId;
        this.capacity = capacity;
        this.currentLevel = 0;
    }
    
    public synchronized void refill(double amount, String supplier) {
        String threadName = Thread.currentThread().getName();
        
        while (currentLevel + amount > capacity) {
            try {
                System.out.println("[" + threadName + "] " + supplier + 
                                 " waiting... Tank " + tankId + " is full (" + 
                                 String.format("%.0f", currentLevel) + "/" + 
                                 String.format("%.0f", capacity) + " liters)");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        currentLevel += amount;
        System.out.println("[" + threadName + "] ⛽ " + supplier + 
                         " refilled Tank " + tankId + " with " + 
                         String.format("%.0f", amount) + " liters");
        System.out.println("[" + threadName + "] Tank " + tankId + " level: " + 
                         String.format("%.0f", currentLevel) + "/" + 
                         String.format("%.0f", capacity) + " liters");
        
        notifyAll();
    }
    
    public synchronized void withdraw(double amount, String aircraft) {
        String threadName = Thread.currentThread().getName();
        
        while (currentLevel < amount) {
            try {
                System.out.println("[" + threadName + "] " + aircraft + 
                                 " waiting for fuel... Tank " + tankId + 
                                 " has only " + String.format("%.0f", currentLevel) + 
                                 " liters (need " + String.format("%.0f", amount) + ")");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        currentLevel -= amount;
        System.out.println("[" + threadName + "] ✈️  " + aircraft + 
                         " withdrew " + String.format("%.0f", amount) + 
                         " liters from Tank " + tankId);
        System.out.println("[" + threadName + "] Tank " + tankId + " level: " + 
                         String.format("%.0f", currentLevel) + "/" + 
                         String.format("%.0f", capacity) + " liters");
        
        notifyAll();
    }
    
    public synchronized double getCurrentLevel() {
        return currentLevel;
    }
    
    public synchronized double getAvailableSpace() {
        return capacity - currentLevel;
    }
    
    public String getTankId() {
        return tankId;
    }
    
    public double getCapacity() {
        return capacity;
    }
}
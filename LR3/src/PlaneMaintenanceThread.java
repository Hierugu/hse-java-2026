public class PlaneMaintenanceThread extends Thread {
    private common.Aeroplane plane;
    
    public PlaneMaintenanceThread(common.Aeroplane plane) {
        this.plane = plane;
        setName("Maintenance-" + plane.getModel());
    }
    
    public PlaneMaintenanceThread(common.Aeroplane plane, String threadName) {
        this.plane = plane;
        setName(threadName);
    }
    
    @Override
    public void run() {
        System.out.println("[" + getName() + "] Starting maintenance for " + plane.getModel());
        
        try {
            // Этап 1: Проверка двигателя
            System.out.println("[" + getName() + "] Checking engine systems...");
            Thread.sleep(1000); // Имитация времени проверки
            
            // Этап 2: Проверка авионики
            System.out.println("[" + getName() + "] Testing avionics...");
            Thread.sleep(800);
            
            // Этап 3: Осмотр планера
            System.out.println("[" + getName() + "] Inspecting airframe...");
            Thread.sleep(1200);
            
            // Этап 4: Проверка топливной системы
            System.out.println("[" + getName() + "] Checking fuel systems...");
            Thread.sleep(600);
            
            // Этап 5: Финальная проверка
            System.out.println("[" + getName() + "] Performing final checks...");
            Thread.sleep(500);
            
            // Выполнение обслуживания
            plane.performMaintenance();
            
            System.out.println("[" + getName() + "] Maintenance completed for " + plane.getModel());
            
        } catch (InterruptedException e) {
            System.out.println("[" + getName() + "] Maintenance interrupted for " + plane.getModel());
            Thread.currentThread().interrupt();
        }
    }
}
public class FlightSimulatorThread extends Thread {
    private common.Aeroplane plane;
    private String destination;
    
    public FlightSimulatorThread(common.Aeroplane plane) {
        this.plane = plane;
        this.destination = "Unknown";
        setName("Flight-" + plane.getModel());
    }
    
    public FlightSimulatorThread(common.Aeroplane plane, String destination) {
        this.plane = plane;
        this.destination = destination;
        setName("Flight-" + plane.getModel() + "-to-" + destination);
    }
    
    @Override
    public void run() {
        System.out.println("[" + getName() + "] Starting flight simulation for " + plane.getModel());
        
        try {
            // Этап 1: Предполетная подготовка
            System.out.println("[" + getName() + "] Pre-flight checks...");
            Thread.sleep(500);
            
            // Этап 2: Запуск двигателей
            System.out.println("[" + getName() + "] Starting engines...");
            plane.start();
            Thread.sleep(300);
            
            // Этап 3: Взлет
            System.out.println("[" + getName() + "] Taking off to " + destination);
            plane.takeOff();
            Thread.sleep(1000);
            
            // Этап 4: Набор высоты
            System.out.println("[" + getName() + "] Climbing to cruise altitude...");
            plane.fly(10000);
            Thread.sleep(1500);
            
            // Этап 5: Крейсерский полет
            System.out.println("[" + getName() + "] Cruising at 10,000m to " + destination);
            for (int i = 0; i < 3; i++) {
                System.out.println("[" + getName() + "] Flight hour " + (i + 1) + " completed");
                plane.addFlightHours(1);
                Thread.sleep(800);
            }
            
            // Этап 6: Снижение
            System.out.println("[" + getName() + "] Descending for landing...");
            Thread.sleep(600);
            
            // Этап 7: Посадка
            System.out.println("[" + getName() + "] Landing at " + destination);
            plane.land();
            Thread.sleep(400);
            
            // Этап 8: Выключение двигателей
            System.out.println("[" + getName() + "] Shutting down engines...");
            plane.stop();
            
            System.out.println("[" + getName() + "] Flight completed! Total hours: " + plane.getFlightHours());
            
        } catch (InterruptedException e) {
            System.out.println("[" + getName() + "] Flight interrupted for " + plane.getModel());
            Thread.currentThread().interrupt();
        }
    }
}
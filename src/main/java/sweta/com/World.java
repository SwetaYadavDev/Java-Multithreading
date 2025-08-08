package sweta.com;

// World is a custom thread class
public class World extends Thread {

    // The run() method contains the code that will execute in a separate thread
    @Override
    public void run() {
        // Infinite loop
        for (;;) {
            System.out.println(" hello google i am coming ");

            // Optional: add a delay to reduce CPU usage
            try {
                Thread.sleep(500); // sleep for 0.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package sweta.com;

public class Test {
    public static void main(String[] args) {
        // Step 1: Create an object of World (a thread)
        World world = new World();

        // Step 2: Start the thread — this calls the run() method in a new thread
        world.start();

        // Step 3: Infinite loop in main thread
        for (;;) {
            System.out.println(" Hello");

            // Optional: add sleep to reduce CPU usage
            try {
                Thread.sleep(500); // sleep for 0.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


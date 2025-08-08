package sweta.com;

import java.util.concurrent.atomic.AtomicInteger;

// Main class to test the counter with multithreading
public class Test1 {
    public static void main(String[] args) {
        // Create a shared Counter instance
        Counter counter = new Counter();

        // Create two threads that will increment the counter
        swetaThread t1 = new swetaThread(counter);
        swetaThread t2 = new swetaThread(counter);

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both threads to finish execution
        try {
            t1.join(); // Waits for thread t1 to complete
            t2.join(); // Waits for thread t2 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter after both threads finish
        System.out.println("Final Count: " + counter.getCount());
    }
}



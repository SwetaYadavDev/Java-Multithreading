package sweta.com;

public class MyThread extends Thread {

    // This method runs in the new thread when start() is called
    @Override
    public void run() {
        System.out.println("Running state"); // Output from the thread
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread(); // Thread created but not started

        // Step 1: Before starting the thread
        // At this point, thread is in NEW state
        System.out.println("Before start(): " + t1.getState()); // Output: NEW

        // Step 2: Start the thread
        // Now JVM will invoke run() in a new thread
        t1.start();

        // Step 3: Sleep main thread to let t1 run and complete
        try {
            Thread.sleep(100); // Let child thread (t1) finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 4: After some delay, check t1 state
        // Since run() finishes fast, t1 is likely TERMINATED
        System.out.println("After sleep(): " + t1.getState()); // Output: TERMINATED (most likely)

        // Step 5: Wait for t1 to finish using join (precaution)
        try {
            t1.join(); // Waits until t1 is 100% done
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 6: Check state again
        System.out.println("After join(): " + t1.getState()); // Output: TERMINATED
    }
}

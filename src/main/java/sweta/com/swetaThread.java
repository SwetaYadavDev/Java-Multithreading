package sweta.com;

// A custom thread class that performs increment operations on a shared Counter
public class swetaThread extends Thread {

    // Shared Counter object between threads
    private Counter counter;

    // Constructor to initialize the counter
    public swetaThread(Counter counter) {
        this.counter = counter;
    }

    // The run() method is executed when the thread starts
    @Override
    public void run() {
        // Increment the counter 1000 times
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

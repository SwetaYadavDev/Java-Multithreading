package sweta.com;

// A thread-safe Counter class using synchronized methods
public class Counter {
    // Shared counter variable
    private int count = 0;

    // Synchronized method to increment the counter safely
    public synchronized void increment() {
        count++;
    }

    // Synchronized method to get the current count (optional, but safer in multi-threaded reads)
    public synchronized int getCount() {
        return count;
    }
}

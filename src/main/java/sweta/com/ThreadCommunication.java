package sweta.com;

// Shared resource between producer and consumer
class SharedResource {
    private int data;               // The data to be shared
    private boolean hasData = false; // Flag to indicate if data is available

    // Method for producer to produce data
    public synchronized void produce(int value) {
        // Wait if data has already been produced and not yet consumed
        while (hasData) {
            try {
                wait(); // Release lock and wait until consumer consumes
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
        this.data = value;        // Set the new value
        hasData = true;           // Mark that data is available
        System.out.println("Produced: " + value);
        notify();                 // Notify waiting consumer
    }

    // Method for consumer to consume data
    public synchronized int consume() {
        // Wait if there's no data to consume
        while (!hasData) {
            try {
                wait(); // Wait until producer produces data
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
            }
        }
        hasData = false;          // Mark that data has been consumed
        System.out.println("Consumed: " + data);
        notify();                 // Notify waiting producer
        return data;              // Return the consumed data
    }
}

// Producer class implementing Runnable
class Producer implements Runnable {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            resource.produce(i); // Produce data from 1 to 10
            try {
                Thread.sleep(500); // Simulate delay in producing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interrupt
            }
        }
    }
}

// Consumer class implementing Runnable
class Consumer implements Runnable {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            resource.consume(); // Consume data 10 times
            try {
                Thread.sleep(700); // Simulate delay in consuming
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle interrupt
            }
        }
    }
}

// Main class to start the producer and consumer threads
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource(); // Create shared object

        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(resource), "Producer");
        Thread consumerThread = new Thread(new Consumer(resource), "Consumer");

        // Start both threads
        producerThread.start();
        consumerThread.start();
    }
}

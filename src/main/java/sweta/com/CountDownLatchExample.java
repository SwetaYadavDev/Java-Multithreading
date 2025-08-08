package sweta.com;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        // CountDownLatch with count = 3 (number of worker threads)
        CountDownLatch latch = new CountDownLatch(3);

        // Start 3 worker threads
        for (int i = 1; i <= 3; i++) {
            int threadId = i;
            new Thread(() -> {
                System.out.println("Thread " + threadId + " is working...");
                try {
                    Thread.sleep(1000 * threadId); // simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + threadId + " finished.");
                latch.countDown(); // signal completion
            }).start();
        }

        System.out.println("Main thread is waiting for all tasks to finish...");
        latch.await(); // wait for all worker threads to call countDown()
        System.out.println("All tasks are finished. Main thread proceeds.");
    }
}

package sweta.com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main2 {
    public static void main(String[] args) {

        // Record the start time before starting all tasks
        long startTime = System.currentTimeMillis();

        // Create a fixed thread pool with 3 threads (you can change as needed)
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Submit factorial tasks to the executor
        for (int i = 1; i < 10; i++) {
            int number = i; // Effectively final for lambda

            executorService.submit(() -> {
                long result = factorial(number);
                System.out.println("Factorial of " + number + " is " + result);
            });
        }

        // Shutdown executor and wait for tasks to finish
        executorService.shutdown();
        try {
            // Wait for all tasks to complete or timeout after 15 seconds
            if (!executorService.awaitTermination(15, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Force shutdown if not finished
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // Record the end time
        long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }

    /**
     * This method calculates the factorial of a given number.
     * It also simulates a time-consuming task by sleeping for 1 second.
     *
     * @param n The number to compute the factorial of.
     * @return The factorial value of the given number.
     */
    private static long factorial(int n) {
        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

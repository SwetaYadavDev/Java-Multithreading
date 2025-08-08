package sweta.com;

import java.util.*;
import java.util.concurrent.*;

public class Main3 {

    public static void main(String[] args) {
        try {
            // Fixed thread pool
            ExecutorService executor = Executors.newFixedThreadPool(3);

            // 1. execute() – no result
            executor.execute(() -> System.out.println("Task using execute()"));

            // 2. submit() – with result
            Future<String> future = executor.submit(() -> {
                Thread.sleep(500);
                return "Result from submit()";
            });
            System.out.println(future.get());

            // 3. invokeAll() – multiple tasks
            List<Callable<String>> taskList = Arrays.asList(
                    () -> "Task 1",
                    () -> "Task 2",
                    () -> "Task 3"
            );
            List<Future<String>> futures = executor.invokeAll(taskList);
            for (Future<String> f : futures) {
                System.out.println("invokeAll: " + f.get());
            }

            // 4. invokeAny() – return first successful result
            String anyResult = executor.invokeAny(taskList);
            System.out.println("invokeAny result: " + anyResult);

            // ScheduledExecutorService
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            // 5. schedule() – delayed execution
            ScheduledFuture<String> scheduledFuture = scheduler.schedule(() -> "Scheduled Task", 1, TimeUnit.SECONDS);
            System.out.println("Scheduled: " + scheduledFuture.get());

            // 6. scheduleAtFixedRate() – periodic execution
            scheduler.scheduleAtFixedRate(() -> System.out.println("Fixed rate task at: " + new Date()),
                    0, 2, TimeUnit.SECONDS);

            // Let the scheduled task run a few times
            Thread.sleep(6000);

            // Gracefully shutdown scheduler
            scheduler.shutdown();
            scheduler.awaitTermination(5, TimeUnit.SECONDS);

            // 7. Shutdown executor
            executor.shutdown(); // orderly shutdown
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                List<Runnable> droppedTasks = executor.shutdownNow(); // force shutdown
                System.out.println("Dropped tasks: " + droppedTasks.size());
            }

            System.out.println("All tasks finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

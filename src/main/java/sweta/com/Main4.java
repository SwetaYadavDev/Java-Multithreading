package sweta.com;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main4 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        try {
            // 1. schedule(Runnable)
            scheduler.schedule(() -> System.out.println("1. Delayed Task (3 sec)"), 3, TimeUnit.SECONDS);

            // 2. schedule(Callable)
            ScheduledFuture<String> futureResult = scheduler.schedule(() -> {
                return "2. Result from Scheduled Callable after 2 seconds";
            }, 2, TimeUnit.SECONDS);
            System.out.println(futureResult.get());

            // 3. scheduleAtFixedRate()
            scheduler.scheduleAtFixedRate(() -> System.out.println("3. Fixed Rate Task at: " + System.currentTimeMillis()),
                    1, 4, TimeUnit.SECONDS);

            // 4. scheduleWithFixedDelay()
            scheduler.scheduleWithFixedDelay(() -> {
                System.out.println("4. Fixed Delay Task starts at: " + System.currentTimeMillis());
                try {
                    Thread.sleep(2000); // simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 2, 5, TimeUnit.SECONDS);

            // Let periodic tasks run for 10 seconds
            Thread.sleep(10000);

            // 5. submit()
            Future<Integer> submitResult = scheduler.submit(() -> 123 + 456);
            System.out.println("5. submit() result: " + submitResult.get());

            // 6. invokeAll()
            List<Callable<String>> callables = Arrays.asList(
                    () -> "6. Task A",
                    () -> "6. Task B",
                    () -> "6. Task C"
            );
            List<Future<String>> results = scheduler.invokeAll(callables);
            for (Future<String> result : results) {
                System.out.println(result.get());
            }

            // 7. invokeAny()
            String fastestResult = scheduler.invokeAny(Arrays.asList(
                    () -> {
                        Thread.sleep(200);
                        return "7. Fast Task";
                    },
                    () -> {
                        Thread.sleep(400);
                        return "7. Slow Task";
                    }
            ));
            System.out.println("invokeAny() result: " + fastestResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 8. Shutdown
                System.out.println("Shutting down...");
                scheduler.shutdown();
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                    List<Runnable> droppedTasks = scheduler.shutdownNow();
                    System.out.println("Dropped Tasks: " + droppedTasks.size());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package sweta.com;

public class ThreadPriority extends Thread {

    public ThreadPriority(String name) {
        super(name); // Set thread name using Thread constructor
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority()
            );
            try {
                Thread.sleep(100); // Sleep to simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPriority high = new ThreadPriority("HighPriorityThread");
        ThreadPriority medium = new ThreadPriority("MediumPriorityThread");
        ThreadPriority low = new ThreadPriority("LowPriorityThread");

        // Set priorities
        high.setPriority(Thread.MAX_PRIORITY);     // 10
        medium.setPriority(Thread.NORM_PRIORITY);  // 5
        low.setPriority(Thread.MIN_PRIORITY);      // 1

        // Start threads
        high.start();
        medium.start();
        low.start();
    }
}

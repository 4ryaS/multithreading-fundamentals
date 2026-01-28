import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static void simulateWebRequest() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting for response");
            // Simulating network delay
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        // Best for I/O bound short tasks
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedPool.execute(() -> {
                simulateWebRequest();
                System.out.println(Thread.currentThread().getName() + " completed I/O task");
            });
        }
        cachedPool.shutdown();
    }
}

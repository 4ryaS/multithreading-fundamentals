import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Starting Task " + taskId);
        try {
            // Simulating different thread states
            // Simulates RUNNABLE -> TIMED_WAITING (Sleep state)
            Thread.sleep(2000);

            synchronized (this) {
                // Thread is now running and enters the synchronized block
                System.out.println(Thread.currentThread().getName() + ": Waiting on Task " + taskId);
                // Simulates WAITING state for 1.5 seconds
                this.wait(1500);
                // Thread leaves the RUNNING state and enters the WAITING state

            }

            // After wait() (by timeout or notify), the thread becomes RUNNABLE again
            // When the scheduler picks it, it re-enters the RUNNING state
            System.out.println(Thread.currentThread().getName() + ": Task " + taskId + " Completed");
        }
        catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
            Thread.currentThread().interrupt();
            // If interrupted while RUNNING, it might transition to TERMINATED or handle the interrupt and continue
            // If interrupted while in TIMED_WAITING or WAITING, it will throw InterruptedException and become RUNNABLE
        }
        // After the try-catch block, if the pool is still active, the thread will likely go back to the RUNNABLE state, waiting for a new task. If the pool is shutting down, it will eventually move to TERMINATED
    }
}

public class ThreadPoolLifecycle {
    public static void main(String[] args) {
        // Creating a thread pool with 4 core threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        System.out.println("Thread Pool Created");
        
        // Submit 10 tasks to the pool
        for (int i = 0; i < 10; i++) {
            // Threads pick tasks and move to RUNNABLE
            executorService.execute(new Task(i));
        }

        // Initiating shutdown after all tasks are submitted
        executorService.shutdown();
        System.out.println("Thread Pool Shutdown Started");

        try {
            // Waiting for all threads to terminate
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                // if shutdownNow is called, threads in the RUNNING state currently will be interrutped
                executorService.shutdownNow();
                System.out.println("Forcing Shutdown");
            }
        }
        catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        // Once shutdown is complete, all threads that were processing tasks (RUNNING, BLOCKED, WAITING, TIMED_WAITING) will have completed their work or been interrupted and will eventually reach the TERMINATED state 
        System.out.println("All threads have been terminated");
    }
}

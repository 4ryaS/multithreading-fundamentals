import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    private final int taskId;

    public WorkerThread(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing task: " + taskId);
        try {
            // Some task execution
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            System.out.println("Task Interrupted: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskId);
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating a fixed thread pool with 4 threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Submiting 10 tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            executorService.submit(new WorkerThread(i));
        }

        // Gracefully shutdown the executor service
        executorService.shutdown();

    }
}

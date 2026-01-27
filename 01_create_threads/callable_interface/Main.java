import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Create ExecutorService with a fixed threadpool
        ExecutorService exec = Executors.newFixedThreadPool(2);

        // Callable instances
        Callable<String> callable1 = new CallableInterface("Task 1");
        Callable<String> callable2 = new CallableInterface("Task 2");

        try {
            // Submit Callable tasks to the executor and get Future objects
            Future<String> future1 = exec.submit(callable1);
            Future<String> future2 = exec.submit(callable2);

            // Get results from Future objects
            System.out.println("Result from the first task: ");
            // Blocks until the task completes
            System.out.println(future1.get());
            
            System.out.println("Result from the second task: ");
            // Blocks until the task completes
            System.out.println(future2.get());
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println("Task execution interrupted: " + e.getMessage());
        }
        // Block of code that will run no matter what, this will not run only when: System.exit(), JVM crashes, thread abruptly dies
        finally {
            // Shutdown the exector
            exec.shutdown();
        }

    }
}

public class ChefThread extends Thread {
    private final Object lock;

    public ChefThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            // Simulating food preparation time
            Thread.sleep(2000);

            synchronized (lock) {
                System.out.println("Chef: Food is ready, notifying the waiter");
                // Wake up the waiting waiter thread
                lock.notify();
            }
        }
        catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
                Thread.currentThread().interrupt();
        }
    }
}

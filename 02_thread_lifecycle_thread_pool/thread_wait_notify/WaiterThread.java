public class WaiterThread extends Thread {
    private final Object lock;

    public WaiterThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("Waiter: Waiting for the food to be ready");
                // Waiter enters the waiting state
                lock.wait();
                System.out.println("Waiter: Food is ready, serving it");
            }
            catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}

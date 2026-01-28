public class ThreadLifecycle extends Thread {
    @Override
    public void run() {
        // RUNNING state: the thread is executing on the CPU
        System.out.println(Thread.currentThread().getName() + " is RUNNING");

        try {
            // TIMED_WAITING: Thread sleeps for a fixed time
            System.out.println(Thread.currentThread().getName() + " is in TIMED_WAITING state");
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (SharedLock.getLock()) {
            try {
                // WAITING state: Thread releases the lock and waits indefinitely until notify/notifyAll
                System.out.println(Thread.currentThread().getName() + " is in WAITING state");
                SharedLock.getLock().wait();
            }
            catch (InterruptedException e) {
                System.out.println("Thread Interrupted!");
                Thread.currentThread().interrupt();
            }

            // TERMINATED state
            System.out.println(Thread.currentThread().getName() + " is TERMINATED");
        }
    }
}

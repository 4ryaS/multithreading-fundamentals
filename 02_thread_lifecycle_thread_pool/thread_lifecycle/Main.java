public class Main {
    public static void main(String[] args) throws InterruptedException {
        // NEW state
        ThreadLifecycle t1 = new ThreadLifecycle();
        ThreadLifecycle t2 = new ThreadLifecycle();

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        // RUNNABLE state
        t1.start();
        t2.start();

        Thread.sleep(2500);

        // TIMED_WAITING state
        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        Thread.sleep(2500);

        // WAITING/BLOCKED state
        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        synchronized (SharedLock.getLock()) {
            SharedLock.getLock().notifyAll();
        }

        t1.join();
        t2.join();

        // TERMINATED state
        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());
    }
}

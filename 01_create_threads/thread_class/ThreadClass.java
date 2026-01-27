public class ThreadClass extends Thread {
    // Override the run method to define the thread behaviour
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running: " + i);
            try {
                Thread.sleep(600);
            }
            catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
        }
    }
}

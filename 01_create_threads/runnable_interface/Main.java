public class Main {
    public static void main(String[] args) {
        // Create runnable instance
        RunnableInterface runnable = new RunnableInterface();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}

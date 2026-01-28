public class SharedLock {
    // This is a shared lock object used for BLOCKED and WAITING states
    private static final Object lock = new Object();

    public static Object getLock() {
        return lock;
    }
}

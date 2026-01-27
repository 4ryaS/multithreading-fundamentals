import java.util.concurrent.Callable;

public class CallableInterface implements Callable<String> {
    private final String name;

    public CallableInterface(String name) {
        this.name = name;
    }

    // Implement the call method from the Callable interface
    @Override
    public String call() throws Exception {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            result.append("Callable ").append(name).append(" is running: ").append(i);
            Thread.sleep(600);
        }
        return result.toString();
    }
}

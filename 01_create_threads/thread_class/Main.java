public class Main {
    public static void main(String[] args) {
        ThreadClass thread1 = new ThreadClass();
        ThreadClass thread2 = new ThreadClass();

        thread1.start();
        thread2.start();
    }   
}

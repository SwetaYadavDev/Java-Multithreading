package sweta.com;

public class LamdaExpression {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(" hello");
        };
        Thread t1= new Thread(runnable);
        t1.start();
    }

}

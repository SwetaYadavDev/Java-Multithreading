package sweta.com;

public class Main1 {
    public static void main(String[] args) {

        // Create a shared bank account with some balance
        BankAccount bankAccount = new BankAccount();

        // Runnable task that withdraws ₹50
        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };
        Thread t1= new Thread(task,"Thread1");
        Thread t2= new Thread(task,"Thread2");
        t1.start();
        t2.start();

    }
}


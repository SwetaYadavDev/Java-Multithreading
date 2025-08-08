package sweta.com;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100; // Initial account balance

    // ReentrantLock is used instead of synchronized to provide more control
    private final Lock lock = new ReentrantLock();

    // Method to withdraw money from the account
    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            // Try to acquire the lock within 1 second (1000ms)
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    // If there is enough balance, proceed with withdrawal
                    if (balance >= amount) {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdraw");

                        // Simulate some processing delay
                        Thread.sleep(3000);

                        balance -= amount; // Deduct amount
                        System.out.println(Thread.currentThread().getName() +
                                " completed withdraw. Remaining balance: " + balance);
                    } else {
                        // Not enough balance
                        System.out.println(Thread.currentThread().getName() + " insufficient balance.");
                    }
                } finally {
                    // Always release the lock inside finally block
                    lock.unlock();
                }
            } else {
                // Could not acquire lock within timeout
                System.out.println(Thread.currentThread().getName() +
                        " could not acquire the lock. Try again later.");
            }
        } catch (InterruptedException e) {
            // Handle thread interruption
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}


package homework5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Table {
    private final Philosopher[] philosophers;
    private final Fork[] forks;
    private final CountDownLatch sitDownLatch;
    private final Semaphore semaphore;

    public Table(String[] names, int eatingTimes) {
        int numberPhilosophers = names.length;
        philosophers = new Philosopher[numberPhilosophers];
        forks = new Fork[numberPhilosophers];
        sitDownLatch = new CountDownLatch(numberPhilosophers);
        semaphore = new Semaphore(2);

        for (int i = 0; i < numberPhilosophers; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < numberPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % numberPhilosophers];
            philosophers[i] = new Philosopher(names[i], leftFork, rightFork, eatingTimes, sitDownLatch, semaphore);
        }
    }

    public void startEating() {
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
    }
}
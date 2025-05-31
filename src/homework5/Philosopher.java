package homework5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;
    private final int eatingTimes;
    private final CountDownLatch sitDownLatch;
    private final Semaphore semaphore;
    private final Random random = new Random();
    private int leftForkUpCount = 0;
    private int rightForkUpCount = 0;
    private int leftForkDownCount = 0;
    private int rightForkDownCount = 0;

    public Philosopher(String name, Fork leftFork, Fork rightFork, int eatingCount, CountDownLatch sitDownLatch, Semaphore semaphore) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatingTimes = eatingCount;
        this.sitDownLatch = sitDownLatch;
        this.semaphore = semaphore;
    }

    private void delay() throws InterruptedException {
        Thread.sleep(2000 + random.nextInt(3000));
    }

    private void think() throws InterruptedException {
        System.out.println(name + " думает...");
        delay();
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " ест спагетти.");
        delay();
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " подходит к столу.");
            delay();
            System.out.println(name + " садится за стол.");
            sitDownLatch.countDown();
            sitDownLatch.await();

            for (int i = 0; i < eatingTimes; i++) {
                think();
                semaphore.acquire();
                leftFork.take();
                leftForkUpCount++;
                System.out.println(name + " берёт левую вилку " + leftForkUpCount + "-й раз.");
                rightFork.take();
                rightForkUpCount++;
                System.out.println(name + " берёт правую вилку " + rightForkUpCount + "-й раз.");
                eat();
                leftFork.put();
                leftForkDownCount++;
                System.out.println(name + " кладёт левую вилку " + leftForkDownCount + "-й раз.");
                rightFork.put();
                rightForkDownCount++;
                System.out.println(name + " кладёт правую вилку " + rightForkDownCount + "-й раз.");
                semaphore.release();
            }
            System.out.println(name + " наелся и уходит.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
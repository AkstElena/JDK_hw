package homework5;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final ReentrantLock lock = new ReentrantLock();

    public void take() {
        lock.lock();
    }

    public void put() {
        lock.unlock();
    }
}
package Task2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzTester {
    public static AtomicInteger counter = new AtomicInteger(1);
    private static final Object MONITOR = new Object();
    private static final int END = 20;
    public static ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(() -> {
            while (counter.get() <= END) {
                synchronized (MONITOR) {
                    if (counter.get() % 3 == 0 && counter.get() % 5 != 0) {
                        queue.add("fizz");
                        counter.incrementAndGet();
                        MONITOR.notifyAll();
                    } else {
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        service.submit(() -> {
            while (counter.get() <= END) {
                synchronized (MONITOR) {
                if (counter.get() % 3 != 0 && counter.get() % 5 == 0) {
                    queue.add("buzz");
                    counter.incrementAndGet();
                    MONITOR.notifyAll();
                } else {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }});
        service.submit(() -> {
            while (counter.get() <= END) {
                synchronized (MONITOR) {
                if (counter.get() % 15 == 0) {
                    queue.add("fizzbuzz");
                    counter.incrementAndGet();
                    MONITOR.notifyAll();
                } else {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }});
        service.submit(() -> {
            while (counter.get() <= END) {
                synchronized (MONITOR) {
                if (counter.get() % 3 != 0 && counter.get() % 5 != 0) {
                    queue.add(counter.get());
                    counter.incrementAndGet();
                    MONITOR.notifyAll();
                } else {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }});
        service.submit(() -> {
            while (!queue.isEmpty()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(queue.poll());
                }
            });
        service.shutdown();
    }
}

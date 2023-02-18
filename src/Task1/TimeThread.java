package Task1;

public class TimeThread extends Thread {
    private final long startTime;
    public TimeThread() {
        startTime = System.currentTimeMillis();
    }

    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            System.out.println("Time passed: " + elapsedTime / 1000 + " seconds");
            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

 class MessageThread extends Thread {
    public void run() {
        int count = 0;
        while (true) {
            if (count % 5 == 0 && count!=0) {
                System.out.println("5 seconds have passed");
            }
            count++;
            try {
                Thread.sleep(1000); // sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        TimeThread timeThread = new TimeThread();
        MessageThread messageThread = new MessageThread();
        timeThread.start();
        messageThread.start();
    }
}
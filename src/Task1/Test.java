package Task1;

public class Test {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CounterIncrement());
        Thread thread2 = new Thread(new MessagesDisplay());
        thread1.start();
        thread2.start();

    }
}

class Program{
    public static int counter = 0;
    public static void sleepForSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MessagesDisplay implements Runnable{
    @Override
    public void run() {
        while (true){
            if (Program.counter%5==0 && Program.counter!=0){
                System.out.println("5 seconds passed");
            }
            Program.sleepForSecond();
        }

    }
}

class CounterIncrement implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("Time passed: " + ++Program.counter);
            Program.sleepForSecond();
        }
    }
}



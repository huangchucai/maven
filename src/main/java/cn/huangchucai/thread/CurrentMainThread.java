package cn.huangchucai.thread;

// 主线程
public class CurrentMainThread {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("current thread: " + t);
        new NewThread("small");
        new NewThread("big");
        t.setName("My Thread");
        System.out.println("After name change :" + t);
        try {
            for (int n = 100; n > 0; n--) {
                Thread.sleep(1000);
                System.out.println("Main Thread: " + n);
            }
        } catch (InterruptedException e) {
            System.out.println("主线程出错");
        }
    }
}

class NewThread extends Thread {
    private final String range;

    NewThread(String range) {
        super("Demo Thread");
        this.range = range;
        System.out.println("Child thread: " + this);
        start();
    }

    public void run() {
        if (range == "small") {
            printSmallWorld();
        } else if (range == "big") {
            printBigWorld();
        }
    }

    private void printSmallWorld() {
        try {
            for (char a = 'a'; a <= 'z'; a++) {
                System.out.println("First Child Thread: " + a);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("子一线程出错");
        }
    }

    private void printBigWorld() {
        try {
            for (char a = 'A'; a <= 'Z'; a++) {
                System.out.println("Two Child Thread: " + a);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("子二线程出错");
        }

    }
}
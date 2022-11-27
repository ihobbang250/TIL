import java.util.ArrayList;

public class ThreadExample implements Runnable {
    int seq;

    public ThreadExample(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq + "thread run");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        System.out.println(this.seq + "thread end");
}

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ThreadExample(i));
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            } catch (InterruptedException e) {}
        }
        System.out.println("main end");
    }
}

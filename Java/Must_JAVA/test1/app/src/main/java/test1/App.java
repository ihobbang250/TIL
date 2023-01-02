package test1;

import java.util.*;

class MyThread extends Thread {
    
    public void run() {
        int sum = 0;
        for (int i = 0; i <10; i++) {
            sum += i;
        }
        String name = Thread.currentThread().getName();
        System.out.println(name +": "+sum);
    }   
}

public class App {

    public static int money = 0;

    public synchronized static void deposit() {
        money++;
    }

    public synchronized static void withdraw() {
        money--;
    }
    public static void main(String[] args) throws InterruptedException{
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int j = random.nextInt(10);
            list.add(j);
        }
        

        int[] nlist = list.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
        
        int sum = Arrays.stream(nlist)
                    .filter(i -> i % 2 == 0)
                    .sum();
        System.out.println(sum);

        List<String> name = new ArrayList<>();
        name = Arrays.asList("해리포터", "기생충", "인간");
        
        name.stream()
            .sorted( (a, b) -> a.length() - b.length())
            .forEach( n -> System.out.println(" " + n));

        
        MyThread t = new MyThread();
        t.start();
        System.out.println("main: " + Thread.currentThread().getName());

        
        Runnable task1 = () -> {
            for (int i = 0; i <10000; i++) {
                deposit();
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 10000; i++) {
                withdraw();
            }
        };
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(money);
    }
}
import java.text.SimpleDateFormat;
import java.util.Date;

class Util {
    public static String getCurrentDate(String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date());
    }
}

class Counter {
    static int count = 0;
    Counter() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

class Singleton {
    private static Singleton one;
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (one == null) {
            one = new Singleton();
        }
        return one;
    }
}

class FinallySample {
    public void shouldBeRun() {
        System.out.println("ok thx");
    }
}

class FoolException extends Exception{
}
class ExceptionExample {
    public void sayNicks(String nick) throws FoolException {
        if ("fool".equals(nick)) {
            throw new FoolException();
        }
        System.out.println("당신의 별명은 "+nick+" 입니다");
    }
}
public class AdvancedExample {
    public static void main(String[] args) throws Exception {
        System.out.println(Util.getCurrentDate("yyyyMMdd"));
        
        Counter c1 = new Counter();
        System.out.println(Counter.getCount()); // Use class to call Static method 

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);

        FinallySample finallysample = new FinallySample();
        int c;
        try {
            c = 4 / 0;
        } catch (ArithmeticException e) {
            c = -1;
        } finally {
            finallysample.shouldBeRun();
        }

        ExceptionExample exceptionExample = new ExceptionExample();
        try {
            exceptionExample.sayNicks("fool");
            exceptionExample.sayNicks("genious");
        } catch (FoolException e) { 
            System.err.println("FoolException발생");
        }
    }
}

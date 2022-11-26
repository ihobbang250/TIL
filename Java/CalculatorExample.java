import java.util.Arrays;
import java.util.ArrayList;

class Calculator {
    int value;

    Calculator() {
        this.value = 0;
    }

    void add(int val) {
        this.value += val;
    }

    int getValue() { 
        return this.value;
    }

    boolean isOdd(int val) {
        if (val % 2 == 0) {
            return false;
        }
        else return true; 
    }

    int avg(int[] data) {
        int result = 0;

        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }

        return result / data.length;
    }

    int avg(ArrayList<Integer> data) {
        int result = 0;
        
        for (int i = 0; i < data.size(); i++) {
            result += data.get(i);
        }
        return result / data.size();
    }

}

class UpgradeCalculator extends Calculator {
    void minus(int val) {
        this.value -= val;
    }
}

class MaxLimitCalculator extends Calculator {
    void add(int val) {
        this.value += val;
        if (this.value >= 10) {
            this.value = 100;
        }
    }
}

public class CalculatorExample {
    public static void main(String[] args) {
        MaxLimitCalculator cal1 = new MaxLimitCalculator();
        cal1.add(50);
        cal1.add(60);
        System.out.println(cal1.getValue());

        Calculator cal2 = new Calculator();
        System.out.println(cal2.isOdd(2));

        int[] data = {1, 3, 5, 7, 9};
        Calculator cal3 = new Calculator();
        int arrayresult = cal3.avg(data);
        System.out.println(arrayresult);

        ArrayList<Integer> data2 = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        int listresult = cal3.avg(data2);
        System.out.println(listresult);

    }
}

import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        
        ArrayList<Integer> aList = new ArrayList<Integer>();
        for (int i = 1; i < a; i++) {
            if ((i % 3 == 0) || (i % 5 == 0)) {
                aList.add(i);
            }
        }

        int result1 = 0;
        int result2 = 0;
        int result3 = 0;

        for (int i = 0; i < aList.size(); i++) {
            result1 += aList.get(i);
        }

        for (int i = 1; i < 1000; i++) {
            if (i % 3 == 0) {
                result2 += i;
            }
            else if(i % 5 == 0) {
                result2 += i;
            }
        }

        for (int i = 1; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                result3 += i;
            }
        }

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}

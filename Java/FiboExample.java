import java.util.Scanner;

public class FiboExample {

    static int fib(int n) {
        int PrefixSum = 1;
        if (n <= 1) {
            return n;
        }
        else if (n == 2) {
            return 1;
        }
        else {
            for (int i = 1; i < n; i++) {
                PrefixSum += i;
            }
            return PrefixSum;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            System.out.println(fib(i));
        }
    }
}
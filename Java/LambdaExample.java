import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

@FunctionalInterface
interface Myfunc {
    int sum(int a, int b);
}

public class LambdaExample{ 
    public static void main(String[] args) {
        Myfunc lamb = (a, b) -> a + b;
        int result = lamb.sum(3, 4);
        System.out.println(result);

        BiFunction<Integer, Integer, Integer> lamb2 = (a, b) -> a + b; // Generics input2, output
        int result2 = lamb2.apply(4, 6); // input, output differet type
        System.out.println(result2);

        BinaryOperator<Integer> lamb3 = (a, b) -> a + b; // Generics input output same type
        int result3 = lamb3.apply(6, 7);
        System.out.println(result3);
    }
}
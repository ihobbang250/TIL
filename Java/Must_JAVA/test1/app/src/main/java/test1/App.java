package test1;

import java.util.*;

public class App {

    public static void main(String[] args) {
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
        
    }
}
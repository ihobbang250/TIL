import java.util.*;

public class IteratorEx {
    public static void main(String[] args) throws Exception {
        List<Integer> v = new Vector<Integer>();
        int sum = 0;
        int sum2 = 0;
        v.add(5);
        v.add(4);
        v.add(-1);
        v.add(2, 100);
        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i));
            sum += v.get(i);
        }
        Iterator<Integer> iterVec = v.iterator();

        while(iterVec.hasNext()) {
            int n = iterVec.next();
            sum2 += n;
            System.out.println(n);
        }
        System.out.println(sum);
        System.out.println(sum2);

    }
}

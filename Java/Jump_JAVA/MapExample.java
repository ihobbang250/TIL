import java.util.HashMap;

public class MapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> a = new HashMap<>();
        a.put("A", 90);
        a.put("B", 80);
        a.put("C", 70);
        System.out.println(a.get("C"));
    }
}
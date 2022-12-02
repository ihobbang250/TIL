import java.util.*;

public class HashMapDicEx {
    public static void main(String[] args) {
        Map <String, String> dic = new HashMap<String, String>();

        dic.put("baby", "아기");
        dic.put("love", "사랑");
        dic.put("apple", "사과");


        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("찾고 싶은 단어는?");
            String word = sc.nextLine();
            if(dic.containsKey(word)) {
                System.out.println(dic.get(word));
                break;
            } else {
                System.out.println(word + "는 없는 단어입니다.");
            }
        }
    }

}

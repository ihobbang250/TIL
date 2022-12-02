import java.util.*;

public class HashTableExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hoyoung", "1234");
        map.put("hobbang", "a456");
        map.put("hooyng", "789");

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("id와 pw입력");
            System.out.print("id 입력: ");
            String id = sc.nextLine();
            System.out.print("pw 입력: ");
            String pw = sc.nextLine();
            System.out.println();

            if(map.containsKey(id)) {
                if(map.get(id).equals(pw)) {
                    System.out.println("로그인되었습니다");
                    break;
                } else {
                    System.out.println("비밀번호 일치하지 않음");
                }
            } else {
                System.out.println("입력한 아이디 존재하지 않음");
            }
        }

    }
}
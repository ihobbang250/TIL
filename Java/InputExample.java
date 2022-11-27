import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) throws IOException {
        System.out.print("저장될 내용 입력: ");
        Scanner sc = new Scanner(System.in);
        String inputline = sc.nextLine();
        FileWriter fw = new FileWriter("sample.txt", true);
        fw.write(String.format("%s\n", inputline));
        fw.close();

        BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
        String readline = br.readLine();
        System.out.println(readline);
        br.close();
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) throws IOException {
        System.out.print("저장될 내용 입력: ");
        Scanner sc = new Scanner(System.in);
        String inputline = sc.nextLine();
        PrintWriter pw = new PrintWriter(new FileWriter("sample.txt", true));
        pw.println(inputline);
        pw.close();
        
        BufferedReader br = new BufferedReader(new FileReader("sample.txt"));
        String readline = br.readLine();
        System.out.println(readline);
        br.close();
        sc.close();
    }
    
}

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Напишите путь к файлу, который нужно зашифровать: ");
        String pathToFile = scan.nextLine();
        System.out.println("Напишите ключ для шифровки: ");
        int codeToEncrypt = scan.nextInt();
        Encoder nEncoder = new Encoder(codeToEncrypt, pathToFile);
        nEncoder.findPathToDirectory();
        nEncoder.Encode();
        System.out.println("Файл закодирован");
    }
}
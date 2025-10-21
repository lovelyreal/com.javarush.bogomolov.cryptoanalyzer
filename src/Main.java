import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Зашифровать");
        System.out.println("2. Расшифровать");
        int result = findCommand();
        if (result == 1) {
            System.out.println("Введите путь к файлу: ");
            String pathToFile = scan.nextLine();
            System.out.println("Введите ключ: ");
            int codeToEncrypt;
            while (!scan.hasNextInt()){
                System.out.println("Введите число");
                scan.nextLine();
            }
            codeToEncrypt = scan.nextInt();
            Encoder encoder = new Encoder(codeToEncrypt, pathToFile);
            encoder.findPathToDirectory();
            encoder.Encode();
        }

    }

    public static int findCommand() {
        Scanner scan = new Scanner(System.in);
        boolean flag = false;
        int intResult = -1;
        while (!flag) {
            String result = scan.next();
            try {
                intResult = Integer.parseInt(result);
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число, введите число.");
            }
        }
        return intResult;
    }
}
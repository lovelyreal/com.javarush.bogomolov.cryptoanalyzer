import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Зашифровать");
        System.out.println("2. Расшифровать");
        System.out.println("3. Расшифровать методом BruteForce");
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
        } else if(result == 2){
            System.out.println("Введите путь к файлу: ");
            String pathToFile = scan.nextLine();
            System.out.println("Введите ключ: ");
            int codeToEncrypt;
            while (!scan.hasNextInt()){
                System.out.println("Введите число");
                scan.nextLine();
            }
            codeToEncrypt = scan.nextInt();
            Uncoder uncoder = new Uncoder(codeToEncrypt, pathToFile);
            uncoder.findPathToDirectory();
            uncoder.Uncode();
        } else if (result == 3) {
            System.out.println("Введите путь к файлу: ");
            String pathToFile = scan.nextLine();
            Uncoder uncoder = new Uncoder(pathToFile);
            uncoder.findPathToDirectory();
            uncoder.UncodeByBruteForce();
        } else {
            System.out.println("Неизвестная команда, программа завершается...");
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
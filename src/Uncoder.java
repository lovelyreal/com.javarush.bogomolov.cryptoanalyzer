import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Uncoder extends AbstractCoder {

    public Uncoder(int codeToEncrypt, String pathToFile) {
        this.codeToEncrypt = codeToEncrypt;
        this.pathToFile = pathToFile;
    }

    public Uncoder(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void Uncode() {
        Scanner scan = new Scanner(System.in);
        File newFile = new File(pathToDirectory.toString() + "Расшифровано с ключом - " + codeToEncrypt + ".txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Файл не создан...");
        }
        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToFile));
             FileWriter fileWriter = new FileWriter(newFile)) {
            while (fileReader.ready()) {
                chars.add(Character.valueOf((char) fileReader.read()));
            }
            for (Character aChar : chars) {
                Character thisChar = Character.toLowerCase(aChar);
                char currentChar;
                int indexOfChar = 0;
                if (!alphabet.contains(thisChar)) {
                    fileWriter.write(thisChar);
                } else {
                    for (int i = 0; i < alphabet.size(); i++) {
                        if (alphabet.get(i).equals(thisChar)) {
                            indexOfChar = i;
                            break;
                        }
                    }
                    currentChar = indexOfChar - (codeToEncrypt % alphabet.size()) >= 0 ? alphabet.get(indexOfChar - (codeToEncrypt % alphabet.size())) : alphabet.get((indexOfChar - (codeToEncrypt % alphabet.size()) + alphabet.size()));
                    fileWriter.write(currentChar);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь на правильность написания)");
            Encoder encoder = new Encoder(codeToEncrypt, scan.nextLine());
            encoder.findPathToDirectory();
            encoder.Encode();
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
            Encoder encoder = new Encoder(codeToEncrypt, scan.nextLine());
        }
    }

    public void UncodeByBruteForce() {
        ArrayList<Character> charsForBruteForce = new ArrayList<>(1024);
        for (int k = 0; k < alphabet.size(); k++) {
            codeToEncrypt = k;
            Scanner scan = new Scanner(System.in);
            File newFile = new File(pathToDirectory.toString() + "Расшифровано с ключом - " + codeToEncrypt + ".txt");
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Файл не создан...");
            }
            try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToFile));
                 FileWriter fileWriter = new FileWriter(newFile)) {
                while (fileReader.ready()) {
                    charsForBruteForce.add(Character.valueOf((char) fileReader.read()));
                }
                for (Character aChar : charsForBruteForce) {
                    Character thisChar = Character.toLowerCase(aChar);
                    char currentChar;
                    int indexOfChar = 0;
                    if (!alphabet.contains(thisChar)) {
                        fileWriter.write(thisChar);
                    } else {
                        for (int i = 0; i < alphabet.size(); i++) {
                            if (alphabet.get(i).equals(thisChar)) {
                                indexOfChar = i;
                                break;
                            }
                        }
                        currentChar = indexOfChar - (codeToEncrypt % alphabet.size()) >= 0 ? alphabet.get(indexOfChar - (codeToEncrypt % alphabet.size())) : alphabet.get((indexOfChar - (codeToEncrypt % alphabet.size()) + alphabet.size()));
                        fileWriter.write(currentChar);
                    }
                }
                charsForBruteForce.clear();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден. Проверьте путь на правильность написания)");
                Uncoder uncoder = new Uncoder(scan.nextLine());
                uncoder.findPathToDirectory();
                uncoder.UncodeByBruteForce();
            } catch (IOException e) {
                System.out.println("Непредвиденная ошибка...");
            }
        }
    }
}

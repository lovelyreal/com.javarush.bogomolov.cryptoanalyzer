import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encoder extends AbstractCoder{
    public int getCodeToEncrypt() {
        return codeToEncrypt;
    }

    public StringBuilder getPathToDirectory() {
        return pathToDirectory;
    }

    public StringBuilder getFileName() {
        return fileName;
    }

    public Encoder(int codeToEncrypt, String pathToFile) {
        this.codeToEncrypt = codeToEncrypt;
        this.pathToFile = pathToFile;
    }

    public void Encode() {
        Scanner scan = new Scanner(System.in);
        File newFile = new File(pathToDirectory.toString() + "Зашифровано с ключом - " + codeToEncrypt + ".txt");
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
                char thisChar;
                int indexOfChar = 0;
                if (!alphabet.contains(aChar)) {
                    fileWriter.write(aChar);
                } else {
                    for (int i = 0; i < alphabet.size(); i++) {
                        if (alphabet.get(i).equals(aChar)){
                            indexOfChar = i;
                            break;
                        }
                    }
                    thisChar = alphabet.get((indexOfChar + codeToEncrypt)%alphabet.size());
                    fileWriter.write(thisChar);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь на правильность написания)");
            Encoder encoder = new Encoder(codeToEncrypt, scan.nextLine());
            encoder.findPathToDirectory();
            encoder.Encode();
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
            Encoder encoder = new Encoder(codeToEncrypt,scan.nextLine());
        }
    }
}

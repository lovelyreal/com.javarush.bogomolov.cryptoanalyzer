import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Encoder {
    private int codeToEncrypt;
    private String pathToFile;
    private StringBuilder pathToDirectory = new StringBuilder();
    private StringBuilder fileName = new StringBuilder();
    private ArrayList<Character> chars = new ArrayList<>(1024);
    private static ArrayList<Character> alphabet = new ArrayList<>();

    static {
        alphabet.add('a');
        alphabet.add('б');
        alphabet.add('в');
        alphabet.add('г');
        alphabet.add('д');
        alphabet.add('е');
        alphabet.add('ё');
        alphabet.add('ж');
        alphabet.add('з');
        alphabet.add('и');
        alphabet.add('й');
        alphabet.add('к');
        alphabet.add('л');
        alphabet.add('м');
        alphabet.add('н');
        alphabet.add('о');
        alphabet.add('п');
        alphabet.add('р');
        alphabet.add('с');
        alphabet.add('т');
        alphabet.add('у');
        alphabet.add('ф');
        alphabet.add('х');
        alphabet.add('ц');
        alphabet.add('ч');
        alphabet.add('ш');
        alphabet.add('щ');
        alphabet.add('ъ');
        alphabet.add('ы');
        alphabet.add('ь');
        alphabet.add('э');
        alphabet.add('ю');
        alphabet.add('я');
        alphabet.add('.');
        alphabet.add(',');
        alphabet.add('"');
        alphabet.add(':');
        alphabet.add('-');
        alphabet.add('!');
        alphabet.add('?');
        alphabet.add(' ');

    }

    public int getCodeToEncrypt() {
        return codeToEncrypt;
    }

    public String getPathToFile() {
        return pathToFile;
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

    public void findPathToDirectory() {
        boolean flag = false;
        for (int i = pathToFile.length() - 1; i >= 0; i--) {
            if (pathToFile.charAt(i) == '\\') {
                flag = true;
            }
            if (flag) {
                pathToDirectory.append(pathToFile.charAt(i));
            } else {
                fileName.append(pathToFile.charAt(i));
            }
        }
        fileName.reverse();
        pathToDirectory.reverse();
    }

    public void Encode() {
        File newFile = new File(pathToDirectory.toString() + "Ключ - " + codeToEncrypt + ".txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Файл не создан...");
        }

        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToFile));
             FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            while (fileReader.ready()) {
                chars.add(Character.valueOf((char) fileReader.read()));
            }
            for (Character aChar : chars) {
                char thisChar;

                if (!alphabet.contains(aChar)) {
                    fileOutputStream.write(aChar);
                } else {
                    int indexOfThisChar = -1;
                    for (int i = 1; i < alphabet.size(); i++) {
                        if (alphabet.get(i) == aChar) {
                            indexOfThisChar = i;
                        }
                    }
                    thisChar = alphabet.get((indexOfThisChar+codeToEncrypt)% alphabet.size());
                    fileOutputStream.write(thisChar);
                }



            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь на правильность написания)");
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
        }
    }
}

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
    private static HashMap<Integer, Character> alphabet =new HashMap<>();
    static {
            alphabet.put(1, 'a');
            alphabet.put(2, 'б');
            alphabet.put(3, 'в');
            alphabet.put(4, 'г');
            alphabet.put(5, 'д');
            alphabet.put(6, 'е');
            alphabet.put(7, 'ё');
            alphabet.put(8, 'ж');
            alphabet.put(9, 'з');
            alphabet.put(10, 'и');
            alphabet.put(11, 'й');
            alphabet.put(12, 'к');
            alphabet.put(13, 'л');
            alphabet.put(14, 'м');
            alphabet.put(15, 'н');
            alphabet.put(16, 'о');
            alphabet.put(17, 'п');
            alphabet.put(18, 'р');
            alphabet.put(19, 'с');
            alphabet.put(20, 'т');
            alphabet.put(21, 'у');
            alphabet.put(22, 'ф');
            alphabet.put(23, 'х');
            alphabet.put(24, 'ц');
            alphabet.put(25, 'ч');
            alphabet.put(26, 'ш');
            alphabet.put(27, 'щ');
            alphabet.put(28, 'ъ');
            alphabet.put(29, 'ы');
            alphabet.put(30, 'ь');
            alphabet.put(31, 'э');
            alphabet.put(32, 'ю');
            alphabet.put(33, 'я');
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
            while(fileReader.ready()){
                chars.add(Character.valueOf((char) fileReader.read()));
            }
            for (Character aChar : chars) {
                char thisChar = aChar;
                thisChar += codeToEncrypt;
                fileOutputStream.write(thisChar);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь на правильность написания)");
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
        }
    }
}

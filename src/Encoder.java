import java.io.*;
import java.util.ArrayList;

public class Encoder {
    private int codeToEncrypt;
    private String pathToFile;
    private StringBuilder pathToDirectory = new StringBuilder();
    private StringBuilder fileName = new StringBuilder();
    private ArrayList<Character> chars = new ArrayList<>(1024);

    public int getCodeToEncrypt() {
        return codeToEncrypt;
    }
    public void setCodeToEncrypt(int codeToEncrypt) {
        this.codeToEncrypt = codeToEncrypt;
    }
    public String getPathToFile() {
        return pathToFile;
    }
    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
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
        File newFile = new File(pathToDirectory.toString() + "Ключ - " +codeToEncrypt + ".txt");
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
            System.out.println("Файл не найден. Проверь на правильность написания)");
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
        }
    }
}

import java.io.*;
import java.util.Scanner;

public class Encoder extends AbstractCoder{
    private char d_OR_uncode;
    public int getCodeToEncrypt() {
        return codeToEncrypt;
    }

    public StringBuilder getPathToDirectory() {
        return pathToDirectory;
    }

    public StringBuilder getFileName() {
        return fileName;
    }

    public Encoder(int codeToEncrypt, String pathToFile, char d_OR_uncode) {
        this.codeToEncrypt = codeToEncrypt;
        this.pathToFile = pathToFile;
        this.d_OR_uncode = d_OR_uncode;
    }
    public Encoder(String pathToFile, char d_OR_uncode) {
        this.pathToFile = pathToFile;
        this.d_OR_uncode = d_OR_uncode;
    }

    public void Encode() {
        Scanner scan = new Scanner(System.in);
        File newFile = new File("");
        if (d_OR_uncode == 'c') {
            newFile = new File(pathToDirectory.toString() + "Зашифровано с ключом - " + codeToEncrypt + ".txt");
        } else if(d_OR_uncode == 'u'){
            newFile = new File(pathToDirectory.toString() + "Расшифровано с ключом - " + codeToEncrypt + ".txt");

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
                        if (alphabet.get(i).equals(thisChar)){
                            indexOfChar = i;
                            break;
                        }
                    }
                    if(d_OR_uncode == 'c') {
                        currentChar = alphabet.get((indexOfChar + codeToEncrypt) % alphabet.size());
                        fileWriter.write(currentChar);
                    } else if(d_OR_uncode == 'u'){
                        currentChar = indexOfChar - (codeToEncrypt % alphabet.size()) >= 0 ? alphabet.get(indexOfChar - (codeToEncrypt % alphabet.size())) : alphabet.get((indexOfChar - (codeToEncrypt % alphabet.size()) + alphabet.size()));
                        fileWriter.write(currentChar);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте путь на правильность написания)");
            Encoder encoder = new Encoder(codeToEncrypt, scan.nextLine(),d_OR_uncode);
            encoder.findPathToDirectory();
            encoder.Encode();
        } catch (IOException e) {
            System.out.println("Непредвиденная ошибка...");
            Encoder encoder = new Encoder(codeToEncrypt,scan.nextLine(),d_OR_uncode);
        }
    }
    public void EncodeByBruteForce(){
        for (int i = 1; i <= alphabet.size() ; i++) {
            Encoder encoder = new Encoder(i, pathToFile,'u');
            encoder.findPathToDirectory();
            encoder.Encode();

        }
    }
}

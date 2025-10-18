import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        StringBuilder pathToDirectory = new StringBuilder();
        StringBuilder fileName = new StringBuilder();
        String pathToFile = "C:\\Games\\aaaa.txt";
        boolean flag = false;
        for (int i = pathToFile.length()-1; i >= 0 ; i--) {
            if(pathToFile.charAt(i) == '\\') {flag = true;}
            if(flag){pathToDirectory.append(pathToFile.charAt(i));}
            else {fileName.append(pathToFile.charAt(i));}
        }
        fileName.reverse();
        pathToDirectory.reverse();
        System.out.println(fileName);
        System.out.println(pathToDirectory);
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(pathToFile));

        ArrayList<Character> chars = new ArrayList<>(1024);
        while(fileReader.ready()){
            chars.add(Character.valueOf((char) fileReader.read()));
        }
        for (Character aChar : chars) {
            System.out.print(aChar);
        }
        File f = new File(pathToDirectory.toString());
        f.createNewFile();

        try(FileOutputStream fileOutputStream = new FileOutputStream(f)){
            for (Character aChar : chars) {
                fileOutputStream.write(aChar);
            }
        }
    }
}
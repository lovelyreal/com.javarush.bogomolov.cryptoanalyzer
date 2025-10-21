import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AbstractCoder {
    protected int codeToEncrypt;
    protected String pathToFile;
    protected StringBuilder fileName = new StringBuilder();
    protected StringBuilder pathToDirectory = new StringBuilder();
    protected static ArrayList<Character> alphabet = new ArrayList<>();
    protected ArrayList<Character> chars = new ArrayList<>(1024);
    static {
        alphabet.add('а');
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
    protected void findPathToDirectory() {
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
    protected void de_Or_uncode(){

    }
}

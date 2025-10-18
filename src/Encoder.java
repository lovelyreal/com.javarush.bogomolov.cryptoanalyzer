public class Encoder {
    private int codeToEncrypt;
    private String pathToFile;
    private StringBuilder pathToDirectory = new StringBuilder();
    private StringBuilder fileName = new StringBuilder();

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

    public Encoder(int codeToEncrypt, String pathToFile){
        this.codeToEncrypt = codeToEncrypt;
        this.pathToFile = pathToFile;
    }
}

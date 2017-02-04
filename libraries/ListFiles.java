import java.io.IOException;
import java.io.File;

public class ListFiles {

    public static void main(String[] args) {
        File dir = new File(".");
        File[] filesList = dir.listFiles();
        for (File file: filesList) { // print out all files including folders
            System.out.println(file.getName());
        }
    }
}

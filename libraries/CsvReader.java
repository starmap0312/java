import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main( String[] args ) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("lines.txt"));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("(" + line[0] + "," + line[1] + "," + line[2] + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

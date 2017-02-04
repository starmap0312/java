import java.io.IOException;
// libraries used in Java8 example
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// libraries used in Java Classic example
import java.io.FileReader;
import java.io.BufferedReader;

public class FilesLines {

    public static void main(String[] args) {
        // Java8 example: read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get("lines.txt"))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Java Classic example: read file into buffer
        try (BufferedReader buffer = new BufferedReader(new FileReader("lines.txt"))) {
            String line = buffer.readLine();
            while (line != null) {
                System.out.println(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

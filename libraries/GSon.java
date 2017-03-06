import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

class Exam {
    private String subject;
    private double grade;

    // Getters and setters are not required for this example.
    // GSON sets the fields directly using reflection.

    @Override
    public String toString() {
        return subject + " - " + grade;
    }
}

class Person {

    private String name;
    private String location;
    private Exam exam;

    @Override
    public String toString() {
        return name + " - " + location + " (" + exam + ")";
    }
}

public class GSon {

    public static void main(String[] args) throws IOException {
        // create a GSON instance used to convert Java and JSON objects conversion
        Gson gson = new GsonBuilder().create();
        // 1) toJson([Java Object, Appendable]): convert Java objects to JSON objects 
        // 1.1) write the converted JSON object to System.out
        gson.toJson(new String("Java String"), System.out);
        System.out.println("");
        // 1.2) write the converted JSON object to to a file (or network stream) 
        try (Writer writer = new FileWriter("sample1.json")) {
            String arr[] = {"abc", "bcd", "efg"};
            gson.toJson(arr, writer);
        }
        // 2) fromJson([Java Object, Appendable]): convert JSON object to Java objects
        try(Reader reader = new InputStreamReader(GSon.class.getResourceAsStream("sample2.json"), "UTF-8")) {
            Person person = gson.fromJson(reader, Person.class);
            System.out.println(person);
        }
    }
}

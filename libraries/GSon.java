import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
        // 1.1) write to System.out
        gson.toJson(new String("Java String"), System.out);
        System.out.println("");
        // 1.2) write to a file (or network stream) 
        try (Writer writer = new FileWriter("sample1.json")) {
            String arr[] = {"abc", "bcd", "efg"};
            gson.toJson(arr, writer);
        }
        // 2) fromJson([Java Object, Appendable]): convert JSON object to Java objects
        try(Reader reader = new InputStreamReader(GSon.class.getResourceAsStream("sample2.json"), "UTF-8")) {
            Person person = gson.fromJson(reader, Person.class);
            System.out.println(person);
        }

        // JsonParser:
        // 3) read JSonObjct
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse("{ \"key1\": {\"key2\": 3}}");
        if (element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            JsonElement element2 = jsonObject.get("key1");
            if (element2.isJsonObject()) {
                JsonObject jsonObject2 = element2.getAsJsonObject();
                System.out.println(jsonObject2.get("key2"));
            }
        }
    }
}

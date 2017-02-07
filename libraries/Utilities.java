import java.net.URL;
import java.io.IOException;

public class Utilities {

    public static void main(String[] args) {
        // 1) Integer.parseInt(str): i.e. C++ atoi(c_str)
        String str1 = new String("123");       // compile error if String is assigned to Integer directly
        Integer num1 = Integer.parseInt(str1);
        System.out.println(num1);
        // 2) Double.parseDouble(str)
        String str2 = new String("123.456");
        Double num2 = Double.parseDouble(str2);
        System.out.println(num2);

        // 3) java.net.URL: the class represents a Uniform Resource Locator, a pointer to a "resource" on World Wide Web 
        try { // URL objects must be caught or declared to be thrown
            URL url = new URL("http://java2s.com:80/index.html"); // may throw IOException
            System.out.println(url);
            System.out.println(url.toURI());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

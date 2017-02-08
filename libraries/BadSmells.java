import java.net.URL;

public class BadSmells {

    public static void main(String[] args) {
        // java.lang.Object.getClass():      return the runtime class of an object
        // java.lang.Class.getClassLoader(): return the class loader for the class
        // java.lang.Class.getResource():    find a resource with a given name
        //BadSmells cls = (new BadSmells()).getClass
        URL url = (new BadSmells()).getClass().getClassLoader().getResource("lines.txt");
        System.out.println(url);
    }
}

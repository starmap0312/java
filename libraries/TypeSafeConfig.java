// com.typesafe.config.Config:
//   it provides API for obtaining values from a Config instance
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

public class TypeSafeConfig {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("example.conf"); 
        // example 1: my.organization object
        System.out.println(config.getString("my.organization.project.description"));
        System.out.println(config.getInt("my.organization.team.age"));
        System.out.println(config.getStringList("my.organization.team.members"));
        // example 2: foo object 
        System.out.println(config.getInt("foo.key.one"));
        System.out.println(config.getInt("dup.key.one"));
        System.out.println(config.getInt("dup.key.two"));
        // example 3: array objects
        System.out.println(config.getIntList("array1"));
        System.out.println(config.getIntList("array2"));
        System.out.println(config.getIntList("array3"));
    }
}

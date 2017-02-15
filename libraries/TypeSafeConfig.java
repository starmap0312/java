// com.typesafe.config.Config:
//   it provides API for obtaining values from a Config instance
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;

public class TypeSafeConfig {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("example.conf"); 
        System.out.println(config.getString("my.organization.project.description"));
        System.out.println(config.getInt("my.organization.team.age"));
        System.out.println(config.getStringList("my.organization.team.members"));
    }
}

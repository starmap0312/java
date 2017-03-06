import java.io.IOException;
import org.json.JSONObject;
import java.util.Iterator;

public class JsonObject {

    public static void main(String[] args) throws Exception {
         String str = "{\"key1\": \"string1\", \"key2\": 2}";
         JSONObject jsonObj = new JSONObject(str);
         // 1) get([key])
         System.out.println(jsonObj.get("key2")); // 2
         // 2) put([key], [value])
         jsonObj.put("key3", "string3");
         System.out.println(jsonObj.toString());  // {"key1":"string1","key2":2,"key3":"string3"}
         // 3) traverse the json object
         Iterator iterator = jsonObj.keys();
         while (iterator.hasNext()) {
             String key = (String) iterator.next();
             System.out.println(jsonObj.get(key));
         }
    }
}

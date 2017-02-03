import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Hashmap {

    public static void main(String[] args) {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();
        // put(key, value): insert key, value pair into the hashmap
        mp.put(3, "three");
        mp.put(2, "two");
        mp.put(1, "one");

        // get(): get value via key 
        System.out.println(mp.get(2)); 

        // remove(): remove value via key 
        mp.remove(2);

        // entrySet().iterator(): iterate the hashmap using Iterator
        Iterator itr = mp.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            System.out.println("(" + e.getKey() + ", " + e.getValue() + ")");
        }
    }
}

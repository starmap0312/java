import java.util.ArrayList;
import java.util.Collections;

public class CollectionsSort {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("efg");
        al.add("bcd");
        al.add("abc");
        Collections.sort(al);
        for (Object o:al) {
            System.out.println(o);
        }
    }
}


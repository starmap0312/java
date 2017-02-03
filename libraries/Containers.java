import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

public class Containers {

    public static void main(String[] args) {

        // Array
        String arr[] = {"abc", "bcd", "efg"};
        for (Object s: arr) {
            System.out.println(s);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // ArrayList<T>
        ArrayList<String> al = new ArrayList<String>();
        al.add("efg");
        al.add("bcd");
        al.add("abc");
        // Collections.sort
        Collections.sort(al);
        for (Object s: al) {
            System.out.println(s);
        }

        // LinkedList<T>
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.add(3);
        ll.add(2);
        ll.add(1);
        for (Object n: ll) {
            System.out.println(n);
        }
    }
}


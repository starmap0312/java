import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.PriorityQueue;

public class Containers {

    public static void main(String[] args) {

        // 1) Array
        String arr[] = {"abc", "bcd", "efg"};
        for (Object s: arr) {
            System.out.println(s);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // 2) ArrayList<T>: implements interface List<T>
        List<String> al = new ArrayList<String>();
        al.add("efg"); al.add("bcd"); al.add("abc");
        Collections.sort(al); // use Collections.sort to sort the List elements
        for (Object s: al) {
            System.out.println(s);
        }
        List<String> al2 = new ArrayList<String>(al); // initialize a List object via another List object

        // 3) LinkedList<T>: implements interface List<T>
        List<Integer> ll = new LinkedList<Integer>();
        ll.add(3); ll.add(2); ll.add(1);
        for (Object n: ll) {
            System.out.println(n);
        }

        // 4) HashSet<T>: implements interface Set<T>
        Set<String> s = new HashSet<String>();
        s.add("d"); s.add("c"); s.add("a");
        Iterator it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 5) HashMap<T1, T2>: implements interface Map<T1, T2>
        Map<Integer, String> mp = new HashMap<Integer, String>();
        // 5.1) put(key, value): insert key, value pair into the hashmap
        mp.put(3, "three"); mp.put(2, "two"); mp.put(1, "one");

        // 5.2) get(): get value via key 
        System.out.println(mp.get(2));

        // 5.3) remove(): remove value via key 
        mp.remove(2);

        // 5.4) entrySet().iterator(): iterate the hashmap using Iterator
        Iterator itr = mp.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            System.out.println("(" + e.getKey() + ", " + e.getValue() + ")");
        }

        // 6) Stack<T>: LIFO
        Stack<Integer> st = new Stack<Integer>();
        st.push(1); st.push(2); st.push(3);
        while (!st.empty()) {
            System.out.println(st.pop());
        }

        // 7) Queue<T>: FIFO
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1); q.add(2); q.add(3);
        while (!q.isEmpty()) {
            System.out.println(q.peek());
            q.remove();
        }

        // 8) PriorityQueue<T>:
        // 8.1) min-heap (default)
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // default: min-heap
        pq.add(5); pq.add(3); pq.add(4); pq.add(1); pq.add(2);
        while (!pq.isEmpty()) {
            System.out.println(pq.peek());
            pq.remove();
        }
        // 8.2) max-heap: PriorityQueue<T>(Comparator<T> comparator)
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Collections.reverseOrder());
        pq2.add(5); pq2.add(3); pq2.add(4); pq2.add(1); pq2.add(2);
        while (!pq2.isEmpty()) {
            System.out.println(pq2.peek());
            pq2.remove();
        }
    }
}


// example 1:
// interface: (filename: Comparable.java)
public interface Comparable<T> {        // <T>: a parameterized/generic type

    public abstract int compareTo(T o); // return <0: this is less than o
                                        // return  0: this is equal to o
                                        // return >0: this is greater than o
}

// implementation: (filename: Person.java)
public class Person implements Comparable<Person> {

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName()); // use String class's compareTo method
    }
}

// example 2:
// interface:
public interface List<T> {

    public abstract T add(T element);
    public abstract int size();
}

public class ListNode<T> {

    ListNode<T> prev;            // recursive data type (default: null)
    ListNode<T> next;            // recursive data type (default: null)
    T data;

    public ListNode(T theData) { // don't need to put generic type <T> in the constructor definition
        this.data = theData;
    }
}

// implementation
public class LinkedList<T> implements List<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public LinkedList() {
        size = 0;
        head = new ListNode<T>(null); // sentinel/dummpy head node
        tail = new ListNode<T>(null); // sentinel/dummpy tail node
        head.next = tail;
        tail.prev = head;
    }

    public T add(T element) {
        // implementation details ...
    }
}

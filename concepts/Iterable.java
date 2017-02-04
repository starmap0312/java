// iterable: has iterator() method
//   a Java construct for accessing elements of a collection
//     i.e. iterate over a source, one element at a time
//   provide an easy way to iterate over elements with a for-each loop
//     i.e. access data using simple, familar sytax and patterns
//   ex.
//     for (Object o: iterable) {
//         ...
//     }
//   
// iterator: has next() method
//   used to iterate over elements with a while-loop
//   ex.
//     while (itr.hasNext()) {
//         ...
//         itr = itr.next();
//     }

// an implementation of Iterable<T>
public class MyIterable<T> implements Iterable<T>{

    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }
}

// an implementation of Iterator<T>
public class MyIterator<T> implements Iterator<T> {

    public boolean hasNext() {
        // ...
    }

    public T next() {
        // ...
    }
}

// use the MyIterable with the for-loop
public static void main(String[] args) {

    MyIterable<String> iterable = new MyIterable<String>();

    for(String string: iterable){

    }
}
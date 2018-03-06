// iterable: has iterator() method
//   a Java construct for accessing elements of a collection
//     i.e. iterate over a source, one element at a time
//   provide an easy way to iterate over elements with a for-each loop (a shorthand)
//     i.e. access data using simple, familar sytax and patterns
//   ex.
//     for (Object o: iterable) {
//         ...
//     }
//   
// iterator: has next() method
//   used to iterate over elements with a while-loop
//   i.e. get the iterator of an iterable by iterator() method, which is used to traverse elements via a while-loop
//   ex.
//     itr = iterable.iterator();
//     while (itr.hasNext()) {
//         ...
//         itr = itr.next();
//     }

public class MyIterable<T> implements Iterable<T>{
    // an implementation of Iterable<T>

    public Iterator<T> iterator() {
        return new MyIterator<T>();
    }
}

public class MyIterator<T> implements Iterator<T> {
    // an implementation of Iterator<T>

    public boolean hasNext() {
        // ...
    }

    public T next() {
        // ...
    }
}

public static void main(String[] args) {

    // 1) Iterable supports for-each loop shorthand to traverse its elements
    MyIterable<String> iterable = new MyIterable<String>();
    for(String string: iterable){
        // ...
    }

    // 2) get the iterator of the iterable and use it to traverse its elements via a while-loop
    MyIterator<String> itr = iterable.iterator();
    while (itr.hasNext()) {
        // ...
        itr = itr.next();
    }    
}


// filename: Comparable.java
public interface Comparable<T> {

    public abstract int compareTo(T o); // return <0: this is less than o
                                        // return  0: this is equal to o
                                        // return >0: this is greater than o
}
// <T>: a generic type

// filename: Person.java
public class Person implements Comparable<Person> {

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName()); // use String class's compareTo method
    }
}

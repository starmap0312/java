
public interface Comparable<T> {

    public abstract int compareTo(T o);
}
// <T>: generic type

public class Person implements Comparable<Person> {

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName()); // use String class's compareTo method
    }
}

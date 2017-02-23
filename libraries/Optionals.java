import java.util.Optional;

public class Optionals {

    public static void main(String[] args) {
        // 1) Optional<T>: JAVA default NULL object (a container object)
        //    use of null implies ambiguous things: no-nexistence, not initialized, or a function's unknown return
        //      this causes many unexpected results and increase the difficulty of debugging 
        //    use of Optional object is a clear indication that it may contain a null value
        //      one can then design the control flow accordingly
        // 1.1) Optional.empty(): static method
        Optional<String> nullObject = Optional.empty(); // create a nullObject intentionally
        if (nullObject.isPresent()) {
            nullObject.get();                           // will thow NoSuchElementException if no value present 
        }
        // 1.2) Optional.of(T): static method
        Optional<String> valueObject = Optional.of("value");
        System.out.println(valueObject.get());

        // 2) chained monadic computations
        Optional<Integer> maybeInteger = Optional.of(1);
        // Function that takes Integer and returns Optional<Integer>
        Optional<Integer> maybePlusOne = maybeInteger.flatMap(x -> Optional.of(x + 1));      // apply the anonymous function to maybeInteger
        // Function that takes Integer and returns Optional<String>
        Optional<String> maybeString = maybePlusOne.flatMap(x -> Optional.of(x.toString())); // apply the anonymous function to maybePlusOne
        System.out.println(maybeString);
    }
}

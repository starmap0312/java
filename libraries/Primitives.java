import java.util.Optional;

public class Primitives {

    public static void main(String[] args) {
        byte      b1 = 100;
        Byte      b2 = 100;
        short     s1 = 123;
        Short     s2 = 123;
        int       i1 = 456;
        Integer   i2 = 456;
        long      l1 = 789L;
        Long      l2 = 789L;
        boolean   t1 = true;
        Boolean   t2 = true;
        char      c1 = 'a';
        Character c2 = 'b';
        float     f1 = 123.4f;
        Float     f2 = 123.4f;
        double    d1 = 456.7d;
        Double    d2 = 456.7d;

        // 1) Integer.parseInt(str): i.e. C++ atoi(c_str)
        String str1 = new String("123");       // compile error if String is assigned to Integer directly
        Integer num1 = Integer.parseInt(str1);
        System.out.println(num1);
        // 2) Double.parseDouble(str)
        String str2 = new String("123.456");
        Double num2 = Double.parseDouble(str2);
        System.out.println(num2);
        // 3) Character.toLowerCase(char), Character.toUpperCase(char)
        //    Character.isLowerCase(char), Character.isUpperCase(char)
        //    Character.isDigit(char), Character.isAlphabetic(char)
        Character c = 'D';
        if (Character.isAlphabetic(c) && Character.isUpperCase(c)) {
            System.out.println(Character.toLowerCase(c));
        }

        // 4) Optional<T>: JAVA default NULL object (a container object)
        //    use of null implies ambiguous things: no-nexistence, not initialized, or a function's unknown return
        //      this causes many unexpected results and increase the difficulty of debugging 
        //    use of Optional object is a clear indication that it may contain a null value
        //      one can then design the control flow accordingly
        // 4.1) Optional.empty(): static method
        Optional<String> nullObject = Optional.empty(); // create a nullObject intentionally
        if (nullObject.isPresent()) {
            nullObject.get();                           // will thow NoSuchElementException if no value present 
        }
        // 4.2) Optional.of(T): static method
        Optional<String> valueObject = Optional.of("value");
        System.out.println(valueObject.get());
    }
}

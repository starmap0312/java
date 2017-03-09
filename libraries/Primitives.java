import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        Integer num1 = 123;
        System.out.println(num1);
        // 1.1) AtomicInteger: support lock-free and thread-safe programming on a single integer variable
        AtomicInteger atomicInt = new AtomicInteger(123);
        System.out.println(atomicInt);

        // 2) Double.parseDouble(str)
        Double num2 = 123.456; 
        System.out.println(num2);
        // 3) Character.toLowerCase(char), Character.toUpperCase(char)
        //    Character.isLowerCase(char), Character.isUpperCase(char)
        //    Character.isDigit(char), Character.isAlphabetic(char)
        Character c = 'D';
        if (Character.isAlphabetic(c) && Character.isUpperCase(c)) {
            System.out.println(Character.toLowerCase(c));
        }

        // 4) method reference (classname::method)
        //    Java 8 introduce the method reference for passing function as an argument to another function 
        //    (before Java 8, functions cannot be referred and passed as an argument)
        //    ex. Math::max, System.out::println
        List<String> list = new ArrayList<String>();
        list.add("efg"); list.add("bcd"); list.add("abc");
        list.forEach(System.out::println);         

        // 5) Date: convert milli-seconds since epoch (integer) to Date
        Date date = new Date(1457991341000L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(date);
        System.out.println(format.format(date));

        // 6) print out the full class name (used with logging)
        System.out.println(String.class.getName()); // java.lang.String
    }
}

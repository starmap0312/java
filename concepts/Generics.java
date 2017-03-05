// C++ templates vs. Java Generic types
// 1) C++: 
//    a) generic functions/classes must be defined in headers
//    b) templates actually completely recompile the code upon construction of the template
//       i.e. the compiler will generate different functions for different types (that it's invoked with)
//            it works by Macro, i.e. generic type are replaced and compiled by the actual types
//    c) pros: it helps with type safty (detected at compile time)
//       ex. you create constraints like type parameter T must have an addition operator: + 
//           if the code it does not have addition operator defined, it will not compile
//    d) cons: compilation is slower, and you may accidentally create undocumented constraints
//       ex. all subclasses must have an addition operator
//           you create constraints like type parameter T must have an addition operator: + 
//           if the code it does not have addition operator defined, it will not compile
// 2) in Java: 
//    Generics in Java s to provide type-checking at compile time and it has no use at run time
//    a) the generic type is erased at runtime by type erasure
//       this ensures that no new classes are created for parameterized types, so there will be no runtime overhead
//    b) Java compiler replaces the bounded type parameter T with the "first bound interface" (upperbound)
//       so only one copy of the code is compiled and all subclasses access the same compiled code
//       ex.
//         <T extends SomeClass> T add(T a, T b) { return a.add ( b ); }
//         (at runtime Java is actually calling)
//         SomeClass add(SomeClass a, SomeClass b) { return a.add ( b ); }
//    c) type parameters can only be reference types, no primitive types: ex. int, double
//    d) pros: it helps with type-safety and provides syntactic sugar for the foreach construct
//       if we specify a type parameter of generic function/class, then
//         incompatible types can be detected at compile time (risk of runtime ClassCastException is avoided)
//         Object type is auto casted to the type parameter (no need to cast the type by ourselves in foreach construct)
// Comparison:
//   C++ : implementation code of generic(template) class/function must be provided in order to use it
//   Java: only signature of generic(template) class/function from a compiled class file is needed in order to use it
//   C++ : static variables are NOT shared between classes of different type parameters
//   Java: static variables are shared between classes of different type parameters (this reduces some pitfalls)
//   C++ : template <typename T> add(T a, T b) { return a + b; }
//         when called with int and double, two copies of code of type int and double are compiled respectively
//   Java: <T extends SomeClass> add(T A, T B) { return A.add(B); }
//         only one copy of the code of type SomeClass is compiled (at runtime all subclasses of SomeClass can invoke add())
// Java Generic Type Naming convention
// E: Element                 (ex. Java Collections: ArrayList<E>, Set<E> etc.)
// K: Key                     (ex. Map<K, V>)
// V: Value                   (ex. Map<K, V>)
// N: Number
// T: Type                    (ex. Comparable<T>)
// S/U/V: 2nd, 3rd, 4th Types
import java.util.List;
import java.util.ArrayList;

interface GenericInterface<T> { // use of generics in interface definition
    // type parameter is erased by erasure, so JVM does not know the actual type T at runtime
    // the generic type T is not bounded, so the code is compiled as T replaced by Object
    public boolean equal(T o);
}

// inherit a generic interface
interface GenericInterfaceSubtype<T> extends GenericInterface<T> {
    public boolean equal(T o);
}

class NoGenericClass {          // no use of generics in class defintion
    private Object data;

    public Object get() {
        return this.data;
    }

    public void set(Object data) {
        this.data = data;
    }
}

class GenericClass<T> {         // use of generics in class definition
    // type parameter is erased by erasure, so JVM does not know the actual type T at runtime
    // the generic type T is not bounded, so the code is compiled as if T replaced by Object
    private T data;

    public T get() {
        return this.data;
    }

    public void set(T data) {
        this.data = data;
    }
}

class BoundedGenericClass<T extends GenericInterface<T>> { // use of bounded generics in class defintion
    private T data;

    public T get() {
        return this.data;
    }

    public void set(T data){
        this.data = data;
    }
}
// Java compiler replaces the bounded type parameter T with the first bound interface, i.e. GenericInterface
// so the above code is compiled as the following:
class CompiledBoundedGenericClass {
    private GenericInterface data;

    public GenericInterface get() {
        return this.data;
    }

    public void set(GenericInterface data) {
        this.data = data;
    }
}

public class Generics {

    public static double sum1(List<Number> list){
        // we cannot call the function by passing in List<Integer> or List<Double>, as they are unrelated to List<Number>
        double sum = 0;
        for(Number n: list){
            sum += n.doubleValue();
        }
        return sum;
    }
    // to make the function more useful, we can write the function using generics upper bounded wildcard 
    public static double sum2(List<? extends Number> list){
        // we can now call the function by passing in List<Integer> or List<Double>
        double sum = 0;
        for(Number n: list){
            sum += n.doubleValue();
        }
        return sum;
    }

    // generic method with unbounded type parameter
    public static <T> boolean isEqual(GenericClass<T> g1, GenericClass<T> g2) { // use of generics in method definition
        // type parameter is erased by erasure, so JVM does not know the actual type T at runtime
        // the generic type T is not bounded, so the code is compiled as if T is replaced by Object
        return g1.get().equals(g2.get());
    }

    // generic method with bounded type parameter
    public static <T extends GenericInterface<T>> boolean equal(T t1, T t2) {   // use of bounded generics in method definition
        // the generic type T is bounded, so the code is compiled as if T is replaced by its upperbound, i.e. GenericInterface
        // if we try to call the method with type parameter that does not implements GenericInterface<T>
        //   it will throw error at compile time
        return t1.equal(t2);
    }

    public static void main(String[] args) {
        // it helps with type-safty
        // example 1: ArrayList[T] 
        // 1.1) raw type (no type parameter)
        List list1 = new ArrayList();      // only one copy of code is compiled, which is of type Object
        list1.add("abc");                  // no type checking, auto casted to String
        list1.add(5);                      // no type checking, auto casted to Integer, receive warning "unchecked call to add(E)"
        for(Object obj: list1) {           // we can use type Object to traverse the collection 
            //String str = (String) obj;   //   will receive runtime exception: ClassCastException (Integer cannot be cast to String)
                                           //   need to do type casting by ourselves
        }
        // 1.2) specify type parameter T as String
        List<String> list2 = new ArrayList<String>();
        list2.add("abc");                  // type checked, can be auto casted to String
        //list2.add(new Integer(5));       // type checked, will receive compiler error: no suitable method found for add(Integer)
        for(String str : list2) {          // syntactic sugar: can use type String to traverse the collection directly 
            System.out.println(str);       //   no type casting is needed (risk of ClassCastException is avoided, when compiled)
        }

        // example 2: generic class
        // 2.1) no use of generics in class definition
        NoGenericClass obj1 = new NoGenericClass();
        obj1.set(new Integer(5));                       // compiled OK: receive no warning
        //System.out.println((String)obj1.get());       // need type casting by ourselves
                                                        // risk of runtime exception: ClassCastException if casting to wrong type
        // 2.2) use of generics in class definition
        //      only one copy of code is compiled, which is also of type Object like the above example
        GenericClass obj2 = new GenericClass();                 // raw type
        GenericClass<String> obj3 = new GenericClass<String>(); // specify type parameter
        obj2.set(10);                                           // no type check, auto casted to Integer: i.e. Integer.valueOf(int)
        obj3.set("abc");                                        // type checked, can be auto casted to String
        //obj3.set(10);                                         // type checked, will receive compiler error
                                                                //   incompatible types: int cannot be converted to String
        System.out.println(obj2.get());
        System.out.println(obj3.get());

        // example 3: generic function/method
        GenericClass<Integer> obj4 = new GenericClass<Integer>();       // specify type parameter
        obj4.set(10);
        System.out.println(Generics.isEqual(obj2, obj4));               // true 
        // type inference: Java compiler is smart enough to determine the variable type to be used
        //   so there is no need to specify the type parameter
        // the above is inferred by compiler as the following:
        System.out.println(Generics.<Integer>isEqual(obj2, obj4));      // true 
        // you cannot compare two different generic types as below:
        // System.out.println(Generics.isEqual(obj3, obj4));            // inferred types are checked: receive compile error
        //   found: GenericClass<String>,GenericClass<Integer>
        //   reason: inferred type does not conform to equality constraint(s)
        
        // example 4: generics upper bounded wildcard
        //   instances of generic type of A can NOT be assigned to instance generic type of B even if A is a subclass of B
        //   because instances of generic classes are not related
        //   so you need to make the inheritance relationship explicit to upper cast an instance to another
        Object obj5 = new String("abc");                         // valid, String IS_A Object
        // however, GenericClass<String> cannot be converted to GenericClass<Object>
        //GenericClass<Object> obj = new GenericClass<String>(); // compile error
        Object obj6 = new GenericClass<String>();                // valid, GenericClass<String> IS_A Object

        List<Number> list3 = new ArrayList<Number>();
        List<Integer> list4 = new ArrayList<Integer>();
        list3.add(1.0); list3.add(2.0); list3.add(3.0);
        list4.add(1);   list4.add(2);   list4.add(3);
        System.out.println(Generics.sum1(list3));   // 6.0
        // however, we cannot pass List<Integer> or List<Double> to Generics.sum1(), as the are not related to List<Number>
        //System.out.println(Generics.sum1(list4)); // error: incompatible types: List<Integer> cannot be converted to List<Number>
        // we can define the function using generics uppder bounded wildward 
        System.out.println(Generics.sum2(list4));   // 6.0 
    }
}

// C++ templates vs. Java Generic types
// 1) C++: 
//      generic functions/classes must be defined in headers
//      templates actually completely recompile the code upon construction of the template
//        i.e. the compiler will generate different functions for different types (that it's invoked with)
//             it works by Macro: i.e. the generic type are replaced and compiled by the actual types
//    Pros: it helps with type safty
//            ex. you create constraints like type parameter T must have an addition operator: + 
//                if the code it does not have addition operator defined, it will not compile
//    Cons: the compilation is slower 
//          you may accidentally create undocumented constraints: ex. all subclasses must have an addition operator
// 2) in Java: 
//    the generic type is erased at runtime by erasure
//      i.e. only one copy of the code is compiled and all subclasses access the same compiled code
//      ex. <T extends Something> T add(T a, T b) { return a.add ( b ); }
//          at runtime Java is actually calling:
//          Something add(Something a, Something b) { return a.add ( b ); }
//    type parameters can only be reference types, no primitive types: ex. int, double
//    Pros: it helps with type-safety
//            i.e. we can specify a parameter type of the generic function/class used
//                 then it will do the runtime casting for us
//                 this removes the risk of getting ClassCastException at runtime
//          it provides some syntactic sugar for the new foreach construct
// Comparison:
//   C++ : implementation code of the template class/function must be provided in order to use it
//   Java: signature of the template class/function from a compiled class file is sufficient to use it
//   C++ : static variables are NOT shared between classes of different type parameters
//   Java: static variables are shared between classes of different type parameters
//   C++ : template <type T> add(T a, T b) { return a + b; }
//         when called with int and double, T will replaced and compiled with int and double respectively
//   Java: <T extends SomeClass> add(T A, T B) { return A.add(B); }
//         at runtime all subclasses of SomeClass can invoke the add() function, so only one copy of the code is compiled
//   
import java.util.List;
import java.util.ArrayList;

class NoGenerics {
    private Object data;

    public Object get() {
        return this.data;
    }

    public void set(Object data) {
        this.data = data;
    }
}

class UseGenerics<T> {
    private T data;

    public T get(){
        return this.data;
    }

    public void set(T data){
        this.data = data;
    }
}

public class Generics {

    public static void main(String[] args) {
        // 1) it helps with type-safty
        // 1.1) without generic type
        List list1 = new ArrayList();
        list1.add("abc");
        list1.add(new Integer(5));         // compiled OK: receive warning unchecked call to add(E)
        for(Object obj: list1){
            // String str = (String) obj;  // we do the type casting by ourselves and gets ClassCastException at runtime
        }
        // 1.2) with generic type
        List<String> list2 = new ArrayList<String>();
        list2.add("abc");
        //list2.add(new Integer(5));       // compiler error: no suitable method found for add(Integer)
        for(String str : list2){
            System.out.println(str);       // no type casting is needed and ClassCastException is avoided
        }

        // 2.1) without generic type
        NoGenerics obj1 = new NoGenerics();
        obj1.set(new Integer(5));                       // compiled OK: receive no warning
        //System.out.println((String)obj1.get());       // we do type casting by ourselves and get ClassCastException at runtime
        // 2.2) with generic type
        UseGenerics<String> obj2 = new UseGenerics<>(); // specify type parameter
        UseGenerics obj3 = new UseGenerics();           // raw type
        //obj2.set(10);                                 // compiler error: incompatible types: int cannot be converted to String
        obj2.set("abc");
        obj3.set(10);                                   // valid and auto casting to Integer: i.e. Integer.valueOf(int)
        System.out.println(obj2.get());
        System.out.println(obj3.get());
    }
}

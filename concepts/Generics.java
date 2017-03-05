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
//    a) the generic type is erased at runtime by erasure
//       only one copy of the code is compiled and all subclasses access the same compiled code
//       ex.
//         <T extends SomeClass> T add(T a, T b) { return a.add ( b ); }
//         (at runtime Java is actually calling)
//         SomeClass add(SomeClass a, SomeClass b) { return a.add ( b ); }
//    b) type parameters can only be reference types, no primitive types: ex. int, double
//    c) pros: it helps with type-safety and provides syntactic sugar for the foreach construct
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
        // 1.2) specify type parameter as String
        List<String> list2 = new ArrayList<String>();
        list2.add("abc");                  // type checked, can be auto casted to String
        //list2.add(new Integer(5));       // type checked, will receive compiler error: no suitable method found for add(Integer)
        for(String str : list2) {          // syntactic sugar: can use type String to traverse the collection directly 
            System.out.println(str);       //   no type casting is needed (risk of ClassCastException is avoided, when compiled)
        }

        // example 2: generic class
        // 2.1) no use of generic type
        NoGenerics obj1 = new NoGenerics();
        obj1.set(new Integer(5));                       // compiled OK: receive no warning
        //System.out.println((String)obj1.get());       // need type casting by ourselves
                                                        // risk of runtime exception: ClassCastException if casting to wrong type
        // 2.2) use of generic type
        //      only one copy of code is compiled, which is also of type Object like the above example
        UseGenerics obj2 = new UseGenerics();                 // raw type
        UseGenerics<String> obj3 = new UseGenerics<String>(); // specify type parameter
        obj2.set(10);                                         // no type check, auto casted to Integer: i.e. Integer.valueOf(int)
        obj3.set("abc");                                      // type checked, can be auto casted to String
        //obj3.set(10);                                       // type checked, will receive compiler error
                                                              //   incompatible types: int cannot be converted to String
        System.out.println(obj2.get());
        System.out.println(obj3.get());
    }
}

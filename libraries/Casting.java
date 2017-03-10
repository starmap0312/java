public class Casting {

    public static void main(String[] args) {
        // [object] instanceof [classname]
        //   a runtime operation used to react differently based on object's type at runtime
        class A {} 
        class B extends A {}
        B obj = new B();
        System.out.println(obj instanceof A); // true

        // casting
        Object obj = "string";                // Object > String
        String str1 = (String) obj;           // downcasting: cast Object to String (they are in the same class hierarchy)
        String str2 = String.class.cast(obj); //              cast Object to String
        //Integer str = (Integer) str;        // compile error: String cannot be converted to Integer
                                              //                (they are in different class hierarchy)
        Number i = new Integer(5);            // Number > Integer and Number > Double 
        Double d = (Double)i;                 // downcasting: cast Number to Double but Integer cannot be casted to Double
                                              //   compiler trust you that Number can be casted to Double at compile time
                                              //   but it throws runtime exception: ClassCastException
    }
}

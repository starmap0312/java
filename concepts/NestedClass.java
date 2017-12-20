// Why Use Nested Classes?
// 1) for encapsulation
//    ex.
//    class A {
//        class B {
//        }
//    }
//    firstly, A's private members remains accessible by B 
//    secondly, class B is hidden from the outside world
// 2) for readable and maintainable code
//    a way of logically grouping classes that are only used in one place
// ex1. a "smart adapter" inner class of an interface
//     if we need a new funcationality of an interface, we add it as an inner supplementary adapter class
interface InputStream {

    int read(byte[] buffer, int offset, int length); // the interface has only one method

    class SingleByte { // a supplementary class that works like a adapter: read(byte[], int, int) => read()

        private final InputStream origin;

        public SingleByte(InputStream stream) {
            this.origin = stream;
        }

        public int read() { // define a read() method, with no input parameter, which reads a single byte
            final byte[] buffer = new byte[1];
            final int read = this.origin.read(buffer, 0, 1);
            final int result;
            if (read < 1) {
                result = -1;
            } else {
                result = buffer[0];
            }
            return result;
        }
    }
}
// the client code
// final InputStream input = new FileInputStream("/tmp/a.txt");
// final byte b = new InputStream.SingleByte(input).read();     // read a single byte from the file input stream

// 2) Static Nested Classes
//    like static class methods, a static nested class cannot refer directly to instance variables or methods defined in its outer class
//    it can use them only through an object reference
class OuterClass {
    class InnerClass {              // a normal nested class for encapsulation

    }

    static class StaticNestedClass { // a static nested class

    } 
}

// ex2. a private static final inner class that is used only by an outer class 
interface Request { }
interface RequestURI { }
final class BaseRequest implements Request {

    private static final class BaseURI implements RequestURI {
        // as BaseURI is only used inside BaseRequest, we put the BaseURI class implementation inside BaseRequest class 

        public RequestURI uri() {
            return new BaseRequest.BaseURI();
        }
    }
}
// the client code: the outside world does not need to know how class BaseURI is implemented
// BaseRequest request = new BaseRequest();
// RequestURI uri = request.uri();

public class NestedClass {
    public static void main(String[] args) {
        // static nested classes are accessed using the enclosing class name
        OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();

        // to instantiate an inner class, you must first instantiate the outer class. then, create the inner object within the outer object with this syntax:
        OuterClass outerObject = new OuterClass();
        OuterClass.InnerClass innerObject = outerObject.new InnerClass();
    }
}

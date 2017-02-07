import java.util.List;
import java.util.ArrayList;
// input -> body
// 1) input:
//        () ->                          ... no input
//        x  ->                          ... one parameter
//    (x, y) ->                          ... two parameters
// 2) body:
//           -> {}                       ... do nothing
//           -> System.out.println("X"); ... print "X"
//           -> {
//              System.out.println("X"); ... print "X" & return 0
//              return 0;
//     }

interface ClassA {
    public void print();
}

public class LambdaExpression {

    public static void main(String[] args) {
        // use of anonymous class
        ClassA a = new ClassA() {
            @Override
            public void print() {
                System.out.println("ClassA");
            }
        };
        a.print();

        // use of lambda expression
        // Object a2 = () -> System.out.println("Lambda");
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.forEach(x -> {
            System.out.println(x);
        });
    }
}

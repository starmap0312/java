// Lambda expression in Java 8
// 1) it facilitates functional programming and simplifies the development
// 2) syntax
//    [parameter] -> [expression body]
// 3) characteristics
//    a) Optional type declaration: No need to declare the type of a parameter (let compiler inference)
//    b) Optional parenthesis for single parameter (parentheses are required for multiple parameters)
//    c) Optional curly braces for single statement: no curly braces is needed if the body contains a single statement
//    d) Optional return keyword for single expression: compiler automatically returns value if body has a single expression
//         (curly braces are required to indicate that expression returns a value)
// 4) examples:
//    parameter:
//        () ->                              ... no input
//       (x) ->                              ... one parameter
//    (x, y) ->                              ... two parameters
//        x  ->                              ... one parameter (syntax suger: no parenthesis is needed)
//    expression body:
//           -> {}                           ... do nothing
//           -> System.out.println("X");     ... print "X"
//           -> {
//                  System.out.println("X"); ... print "X" & return 0
//                  return 0;
//              }
//           -> single expression;           ... return value for single expression (syntax suger: no return keyword is needed)
import java.util.List;
import java.util.ArrayList;

interface NoReturnFunction {
    public void eval();
}

interface ReturnIntFunction {
    public int eval();
}

interface SingleParameterFunction {
    public int eval(int x);
}

public class LambdaExpression {

    public static void main(String[] args) {
        // 1) creat a function object via anonymous class
        NoReturnFunction func = new NoReturnFunction() {
            @Override
            public void eval() {
                System.out.println("create function object via anonymous class");
            }
        };
        func.eval();

        // 2) create function object via lambda expression
        // 2.1) () -> statement: no parameter and return void
        NoReturnFunction func2 = () -> System.out.println("create no return function object via lambda expression");
        func2.eval();

        // 2.2) () -> { statement; return -1; }
        ReturnIntFunction func3 = () -> {
            System.out.println("create return int function object via lambda expression");
            return -1;
        }; 
        System.out.println("retrun = " + func3.eval());

        // 2.3) (x) -> { statement; return x; }
        SingleParameterFunction func4 = (x) -> {
            System.out.println("create single parameter function object via lambda expression");
            return x;
        }; 
        System.out.println("retrun = " + func4.eval(4));

        // 2.4) x -> { x * x; }                      ... use syntasx suger for single parameter and expression function
        SingleParameterFunction func5 = x -> x * x; 
        System.out.println("retrun = " + func5.eval(4));

        // 3) pass function object to Container's forEach() method 
        List<String> list = new ArrayList<String>();
        list.add("one"); list.add("two"); list.add("three");
        list.forEach(x -> System.out.println(x));
    }
}

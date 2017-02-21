import scala.Tuple3;

public class Scala {

    public static void main(String[] args) {
        Tuple3<Boolean, Integer, Double> tuple = new Tuple3<Boolean, Integer, Double>(false, 1, 2.0);
        System.out.println(tuple);
        // note: _1, _2, _3 are private fields in Java
        System.out.println(tuple._1());
        System.out.println(tuple._2());
        System.out.println(tuple._3());
    }
}

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randoms {

    public static void main(String[] args) {
        Integer min = 0, max = 100;
        // Before Java 1.7
        Random rand = new Random();
        Integer randomNum1 = rand.nextInt(max - min + 1) + min;
        System.out.println(randomNum1);

        // Java 1.7 or later
        Integer randomNum2 = ThreadLocalRandom.current().nextInt(min, max + 1);
        System.out.println(randomNum2);
        // advantage: no need to explicitly initialize a java.util.Random instance
        //   which can be a source of confusion and error if used inappropriately
    }
}

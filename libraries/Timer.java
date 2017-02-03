public class Timer {

    public static void main(String[] args) {
        // 1) System.currentTimeMillis(): elapsed time in milliseconds since the epoch
        System.out.println(System.currentTimeMillis());
        // 2) System.nanoTime(): elapsed time in nanoseconds since some fixed but arbitrary time (may be negative)
        System.out.println(System.nanoTime());
    }
}

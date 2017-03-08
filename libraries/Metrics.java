// Meters:
// 1) meters measure the rate of events over time (ex. requests per second)
// 2) meters also track 1-, 5-, and 15-minute moving averages
import java.util.concurrent.TimeUnit;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;

public class Metrics {
    static final MetricRegistry metrics = new MetricRegistry(); // 1) create a registry instance

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter              // 2) create a reporter instance
            .forRegistry(metrics)                               //      under which registry
            .convertRatesTo(TimeUnit.SECONDS)                   //      specify rate time unit
            .convertDurationsTo(TimeUnit.MILLISECONDS)          //      specify duration time unit
            .build();
        reporter.start(1, TimeUnit.SECONDS);                    // 3) specify reporter periodicity and start it 
    }

    static void wait3Seconds() {
        try {
            Thread.sleep(3 * 1000);
        } catch(InterruptedException e) {}
    }

    public static void main(String[] args) {
        startReport();
        Meter requests = metrics.meter("requests/sec");         // 4) create a meter instance using the registry
        requests.mark();                                        // 5) make a mark on the meter (count++)
        wait3Seconds();
    }
}

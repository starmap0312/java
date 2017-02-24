// Meters:
// 1) meters measure the rate of events over time (ex. requests per second)
// 2) meters also track 1-, 5-, and 15-minute moving averages
import java.util.concurrent.TimeUnit;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;

public class Metrics {
    static final MetricRegistry metrics = new MetricRegistry();

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter
            .forRegistry(metrics)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static void wait3Seconds() {
        try {
            Thread.sleep(3 * 1000);
        } catch(InterruptedException e) {}
    }

    public static void main(String[] args) {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        wait3Seconds();
    }
}

// Meters:
// 1) meters measure the rate of events over time (ex. requests per second)
// 2) meters also track 1-, 5-, and 15-minute moving averages
import java.util.concurrent.TimeUnit;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;

public class Metrics {
    static final MetricRegistry registry = new MetricRegistry(); // 1) create a registry instance

    static void startReport() {
        // 1) ConsoleReporter: reporting to the console
        ConsoleReporter reporter = ConsoleReporter               // 2) create a reporter instance
            .forRegistry(registry)                               //      under which registry (binding to a created registry instance)
            .convertRatesTo(TimeUnit.SECONDS)                    //      specify rate time unit
            .convertDurationsTo(TimeUnit.MILLISECONDS)           //      specify duration time unit
            .build();
        reporter.start(1, TimeUnit.SECONDS);                     // 3) specify reporter periodicity and start it 
        // 2) JmxReporter: reporting Via JMX
        //    once reporter is started, all of the metrics in the registry will become visible via JConsole or VisualVM (for MBeans plugin)
        //
        //    JmxReporter reporter = JmxReporter.forRegistry(registry).build();
        //    reporter.start();
    }

    static void wait3Seconds() {
        try {
            Thread.sleep(3 * 1000);
        } catch(InterruptedException e) {}
    }

    public static void main(String[] args) {
        startReport();
        // 1) Meter
        //    it measures the rate of events over time (ex. requests per second)
        //    in addition to the mean rate, meters also track 1-, 5-, and 15-minute moving averages
        Meter counter = registry.meter("counter");         // create a meter instance using the registry (binding a Meter to a MetricRegistry)
        // 2) Timer
        //    it measures the amount of time it takes to process each request in nanoseconds
        //    it provides a rate of requests in requests per second
        Timer timer = registry.timer("timer");

        final Timer.Context context = timer.time();
        counter.mark();                                    // make a mark on the meter (count++)
        wait3Seconds();                                    // a costly operation
        context.stop();
    }
}

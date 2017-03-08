import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    private Logger logger = LoggerFactory.getLogger(getClass()); // note: non-static method getClass() cannot be referenced from static context 

    public void log() {
        logger.warn("message");      // [main] WARN Logging - message
    }

    public static void main(String[] args) {
        Logging logging = new Logging();
        logging.log();
    }
}

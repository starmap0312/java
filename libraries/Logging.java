// Logging Level:
//   "FATAL" > "ERROR" > "WARN" > INFO > DEBUG > TRACE
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    // LoggerFactory.getLogger([classname])
    private static Logger logger = LoggerFactory.getLogger(Logging.class.getName());
    private static Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public void writeLog() {
        System.out.println(Logging.class.getName());
        logger.error("an error message");      // [main] ERROR Logging - message
        logger.warn("a warning message");      // [main] WARN Logging - message
        logger.debug("a debug message");       // [main] INFO Logging - message
        rootLogger.error("an error message");      // [main] ERROR Logging - message
        rootLogger.warn("a warning message");      // [main] WARN Logging - message
        rootLogger.debug("a debug message");       // [main] INFO Logging - message
    }

    public static void main(String[] args) {
        Logging logging = new Logging();
        logging.writeLog();
    }
}

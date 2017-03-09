// Logging Level:
//   "FATAL" > "ERROR" > "WARN" > INFO > DEBUG > TRACE
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    // LoggerFactory.getLogger([classname])
    //private Logger logger = LoggerFactory.getLogger(getClass());
    // note: non-static method getClass() cannot be referenced from static context 
    //private static Logger logger = LoggerFactory.getLogger("com.domain.mypackage");
    private static Logger logger = LoggerFactory.getLogger(Logging.class);

    public void writeLog() {
        logger.error("an error message");      // [main] ERROR Logging - message
        logger.warn("a warning message");      // [main] WARN Logging - message
        logger.debug("a debug message");       // [main] INFO Logging - message
    }

    public static void main(String[] args) {
        Logging logging = new Logging();
        logging.writeLog();
    }
}

package log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class TestLog {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        try {
            Class.forName("hello");
        } catch (ClassNotFoundException e) {
            LOGGER.error("error", e);
        }
    }
}

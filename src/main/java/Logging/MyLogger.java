package Logging;

import java.util.logging.Logger;

public class MyLogger {
    private static Logger logger;

    public static Logger getFileLogger() {
        if (logger == null) {
            System.setProperty("java.util.logging.config.file",
                    "src/main/resources/logger.properties");
            logger = Logger.getLogger(MyLogger.class.getName());
        }
        return logger;
    }
}
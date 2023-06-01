package com.qac.maven_selenium_pom.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    private static final Logger logger = LogManager.getLogger(LogHelper.class);

    public static void logInfo(String message) {
        logger.info("Info: " + message);
    }

    public static void logWarn(String message) {
        logger.warn("Warn: " + message);
    }

    public static void logError(String message) {
        logger.error("Error: " + message);
    }

    public static void logFatal(String message) {
        logger.fatal("Fatal: " + message);
    }

    public static void main(String[] args) {
        logInfo("This is an info message");
        logWarn("This is a warning message");
        logError("This is an error message");
        logFatal("This is a fatal message");
    }
}
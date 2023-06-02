package com.qac.maven_selenium_pom.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepLogger {
    private static final Logger logger = LogManager.getLogger("StepsLogger");

    public static void logStep(String message) {
        logger.info("Step: " + message);
    }

    public static void main(String[] args) {
        logStep("This is a step message");
    }
}
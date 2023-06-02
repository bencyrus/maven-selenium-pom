package com.qac.maven_selenium_pom.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log4j2Config {

    public static void configure() {
        ConfigurationBuilder<BuiltConfiguration> builder =
                org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.setStatusLevel(Level.WARN);
        builder.setConfigurationName("Config");

        // Create a layout
        LayoutComponentBuilder standardLayout = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n");

        // Create and add a console appender
        AppenderComponentBuilder consoleAppender = builder.newAppender("Console", "CONSOLE")
                .add(standardLayout);
        builder.add(consoleAppender);

        // Generate dynamic filename
        String fileName = "logs/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".log";

        // Create and add a file appender
        AppenderComponentBuilder fileAppender = builder.newAppender("FileLogger", "File")
                .addAttribute("fileName", fileName)
                .add(standardLayout);
        builder.add(fileAppender);

        // Create a layout for steps
        LayoutComponentBuilder stepsLayout = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n");

        // Generate dynamic filename for steps
        String stepsFileName = "logs/steps/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".log";

        // Create and add a file appender for steps
        AppenderComponentBuilder stepsFileAppender = builder.newAppender("StepsFileLogger", "File")
                .addAttribute("fileName", stepsFileName)
                .add(stepsLayout);
        builder.add(stepsFileAppender);

        // Create and add a new logger for steps
        builder.add(builder.newLogger("StepsLogger", Level.INFO)
                .add(builder.newAppenderRef("StepsFileLogger"))
                .addAttribute("additivity", false));  // Do not inherit appenders from root logger

        // Create and add a new root logger
        builder.add(builder.newRootLogger(Level.TRACE)
                .add(builder.newAppenderRef("Console"))
                .add(builder.newAppenderRef("FileLogger")));

        // Setup LoggerContext
        Configurator.initialize(builder.build());
    }
}

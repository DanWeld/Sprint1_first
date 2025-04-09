package shared.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {
    private static AppLogger instance;
    private final org.apache.logging.log4j.Logger log4jLogger;
    private final String logDirectory = "logs";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private AppLogger() {
        log4jLogger = LogManager.getLogger(AppLogger.class);
        createLogDirectory();
    }

    public static AppLogger getInstance() {
        if (instance == null) {
            synchronized (AppLogger.class) {
                if (instance == null) {
                    instance = new AppLogger();
                }
            }
        }
        return instance;
    }

    private void createLogDirectory() {
        File dir = new File(logDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public void log(String message, String ipAddress) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String logMessage = String.format("[%s] [IP: %s] %s", timestamp, ipAddress, message);
        
        // Log to console
        log4jLogger.info(logMessage);
        
        // Log to file
        String date = LocalDateTime.now().format(dateFormatter);
        String fileName = logDirectory + "/" + date + ".log";
        
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            log4jLogger.error("Failed to write to log file: " + e.getMessage());
        }
    }
} 
package summerhouse.booking.serverModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerLogger {
    private static ServerLogger instance;
    private static final String LOG_FILE_PATH = "server_logs.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ServerLogger() {}

    public static synchronized ServerLogger getInstance() {
        if (instance == null) {
            instance = new ServerLogger();
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String ipAddress = getIpAddress();
        String logEntry = String.format("%s - %s - %s", timestamp, ipAddress, message);

        // Log to console
        System.out.println(logEntry);

        // Log to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown IP";
        }
    }
} 
package serverModel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLogger {
    private static ServerLogger instance;
    private PrintWriter writer;

    private ServerLogger() {
        try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            writer = new PrintWriter(new FileWriter("server-log-" + date + ".txt", true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ServerLogger getInstance() {
        if (instance == null) {
            instance = new ServerLogger();
        }
        return instance;
    }

    public synchronized void log(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = timestamp + " - " + message;
        System.out.println(logMessage);
        writer.println(logMessage);
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}

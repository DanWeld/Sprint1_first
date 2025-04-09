package serverNetwork;

import serverModel.ServerBookingModel;
import serverModel.ServerLogger;
import com.google.gson.Gson;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private ServerSocket serverSocket;
    private ServerBookingModel model;
    private int port = 12345;
    private ExecutorService executorService;
    private Gson gson;
    private ServerLogger logger = ServerLogger.getInstance();

    public SocketServer(ServerBookingModel model) {
        this.model = model;
        this.executorService = Executors.newCachedThreadPool();
        this.gson = new Gson();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            logger.log("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.log("Client connected: " + clientSocket.getInetAddress());
                ServerSocketHandler handler = new ServerSocketHandler(clientSocket, model, gson);
                executorService.execute(handler);
            }
        } catch (Exception e) {
            logger.log("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                logger.log("Server closed.");
            }
            executorService.shutdown();
        } catch (Exception e) {
            logger.log("Error while closing server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

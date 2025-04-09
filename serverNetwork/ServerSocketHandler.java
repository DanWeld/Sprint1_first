package serverNetwork;

import serverModel.ServerBookingModel;
import serverModel.ServerObserver;
import serverModel.ServerLogger;
import serverModel.OperationStrategy;
import com.google.gson.Gson;
import shared.Booking;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerSocketHandler implements Runnable, ServerObserver {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerBookingModel model;
    private Gson gson;
    private ServerLogger logger = ServerLogger.getInstance();
    private Map<String, OperationStrategy> strategies;

    public ServerSocketHandler(Socket socket, ServerBookingModel model, Gson gson) {
        this.socket = socket;
        this.model = model;
        this.gson = gson;
        this.strategies = new HashMap<>();
        strategies.put("bookProperty", new BookPropertyStrategy(model));
        strategies.put("cancelBooking", new CancelBookingStrategy(model));
        strategies.put("processPayment", new PaymentProcessingStrategy(model));
    }

    @Override
    public void update(String message) {
        try {
            out.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            model.addObserver(this);
            logger.log("Handler started for client: " + socket.getInetAddress());

            while (true) {
                String request = (String) in.readObject();
                logger.log("Received request: " + request);
                // Parse JSON request
                Map<String, String> requestData = gson.fromJson(request, Map.class);
                String operation = requestData.get("operation");
                String data = requestData.get("data");

                OperationStrategy strategy = strategies.get(operation);
                String response = strategy != null ? strategy.execute(data) : "Invalid operation";

                out.writeObject(response);
            }
        } catch (Exception e) {
            logger.log("Error in handler: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                model.removeObserver(this);
                socket.close();
                logger.log("Handler closed for client: " + socket.getInetAddress());
            } catch (Exception e) {
                logger.log("Error while closing handler: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

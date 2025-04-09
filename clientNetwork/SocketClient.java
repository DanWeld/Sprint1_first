package clientNetwork;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String host = "localhost";
    private int port = 12345;

    @Override
    public boolean bookProperty(String details) {
        // Logic to send booking request to server
        return true;
    }

    @Override
    public boolean cancelBooking(String details) {
        // Logic to send cancel request to server
        return true;
    }

    @Override
    public boolean processPayment(String details) {
        // Logic to send payment request to server
        return true;
    }

    @Override
    public void startClient() {
        // Logic to start client connection
    }
}

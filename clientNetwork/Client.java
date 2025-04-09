package clientNetwork;

public interface Client {
    boolean bookProperty(String details);
    boolean cancelBooking(String details);
    boolean processPayment(String details);
    void startClient();
}

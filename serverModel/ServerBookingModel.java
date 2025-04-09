package serverModel;

public interface ServerBookingModel {
    boolean bookProperty(String details);
    boolean cancelBooking(String details);
    boolean processPayment(String details);
}

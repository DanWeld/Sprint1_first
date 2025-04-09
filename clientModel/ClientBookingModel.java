package clientModel;

public interface ClientBookingModel {
    boolean bookProperty(String details);
    boolean cancelBooking(String details);
    boolean processPayment(String details);
}

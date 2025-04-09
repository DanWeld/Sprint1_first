package serverModel;

public class CancelBookingStrategy implements OperationStrategy {
    private ServerBookingModel model;

    public CancelBookingStrategy(ServerBookingModel model) {
        this.model = model;
    }

    @Override
    public String execute(String data) {
        boolean success = model.cancelBooking(data);
        return success ? "Booking canceled successfully" : "Failed to cancel booking";
    }
}

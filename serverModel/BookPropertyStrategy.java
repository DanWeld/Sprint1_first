package serverModel;

public class BookPropertyStrategy implements OperationStrategy {
    private ServerBookingModel model;

    public BookPropertyStrategy(ServerBookingModel model) {
        this.model = model;
    }

    @Override
    public String execute(String data) {
        boolean success = model.bookProperty(data);
        return success ? "Property booked successfully" : "Failed to book property";
    }
}

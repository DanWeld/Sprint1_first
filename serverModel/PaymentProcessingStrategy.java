package serverModel;

public class PaymentProcessingStrategy implements OperationStrategy {
    private ServerBookingModel model;

    public PaymentProcessingStrategy(ServerBookingModel model) {
        this.model = model;
    }

    @Override
    public String execute(String data) {
        boolean success = model.processPayment(data);
        return success ? "Payment processed successfully" : "Failed to process payment";
    }
}

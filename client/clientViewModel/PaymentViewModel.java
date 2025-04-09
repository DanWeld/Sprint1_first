package client.clientViewModel;

import clientModel.ClientBookingModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaymentViewModel {
    private ClientBookingModel model;
    private StringProperty paymentDetails;

    public PaymentViewModel(ClientBookingModel model) {
        this.model = model;
        this.paymentDetails = new SimpleStringProperty();
    }

    public StringProperty paymentDetailsProperty() {
        return paymentDetails;
    }

    public void processPayment() {
        model.processPayment(paymentDetails.get());
    }
}

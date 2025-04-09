package client.clientViewModel;

import clientModel.ClientBookingModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingViewModel {
    private ClientBookingModel model;
    private StringProperty bookingDetails;

    public BookingViewModel(ClientBookingModel model) {
        this.model = model;
        this.bookingDetails = new SimpleStringProperty();
    }

    public StringProperty bookingDetailsProperty() {
        return bookingDetails;
    }

    public void bookProperty() {
        model.bookProperty(bookingDetails.get());
    }

    public void cancelBooking() {
        model.cancelBooking(bookingDetails.get());
    }
}

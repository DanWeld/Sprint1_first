package clientView;

import core.ViewHandler;
import core.ViewModelFactory;
import client.clientViewModel.BookingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookingController implements ViewController {
    @FXML
    private TextField bookingDetails;

    private ViewHandler viewHandler;
    private BookingViewModel bookingViewModel;

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.bookingViewModel = viewModelFactory.getBookingViewModel();
    }

    @FXML
    public void onBookButton() {
        bookingViewModel.bookProperty();
    }

    @FXML
    public void onCancelButton() {
        bookingViewModel.cancelBooking();
    }
}

package summerhouse.booking.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import summerhouse.booking.client.viewmodel.MainViewModel;
import summerhouse.booking.shared.model.Booking;
import summerhouse.booking.shared.model.Property;
import java.time.LocalDate;

public class AdditionalViewController {
    @FXML
    private TableView<Property> propertyTable;
    @FXML
    private TableView<Booking> bookingTable;
    private MainViewModel viewModel;

    public void init(MainViewModel viewModel) {
        this.viewModel = viewModel;
        propertyTable.setItems(viewModel.getProperties());
        bookingTable.setItems(viewModel.getBookings());
    }

    @FXML
    private void onAddBooking() {
        Property selectedProperty = propertyTable.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            // Logic to add a booking
            viewModel.createBooking(
                selectedProperty,
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                2 // Number of guests
            );
        }
    }

    @FXML
    private void onCancelBooking() {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            // Logic to cancel a booking
            viewModel.cancelBooking(selectedBooking);
        }
    }
} 
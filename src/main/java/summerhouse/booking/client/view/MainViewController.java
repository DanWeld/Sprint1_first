package summerhouse.booking.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import summerhouse.booking.client.viewmodel.MainViewModel;
import summerhouse.booking.shared.model.Booking;
import summerhouse.booking.shared.model.Customer;
import summerhouse.booking.shared.model.Property;

public class MainViewController {
    @FXML
    private TableView<Property> summerhouseTable;
    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableView<Customer> customerTable;
    private MainViewModel viewModel;

    public void init(MainViewModel viewModel) {
        this.viewModel = viewModel;
        summerhouseTable.setItems(viewModel.getProperties());
        bookingTable.setItems(viewModel.getBookings());
    }

    @FXML
    private void onRefresh() {
        try {
            viewModel.loadProperties();
            viewModel.loadUserBookings();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Show error dialog
        }
    }

    @FXML
    private void onViewDetails() {
        // Logic to view customer details
    }

    @FXML
    private void onMarkAsPaid() {
        // Logic to mark a customer as paid
    }
} 
package summerhouse.booking.client.viewmodel;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import summerhouse.booking.shared.model.Booking;
import summerhouse.booking.shared.model.Property;
import summerhouse.booking.shared.network.ISummerhouseService;

public class MainViewModel {
    private final ISummerhouseService summerhouseService;
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final ObservableList<Property> properties = FXCollections.observableArrayList();
    private final ObservableList<Booking> bookings = FXCollections.observableArrayList();
    private final StringProperty errorMessage = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private final IntegerProperty guests = new SimpleIntegerProperty();

    public MainViewModel(ISummerhouseService summerhouseService) {
        this.summerhouseService = summerhouseService;
    }

    public void login() {
        try {
            summerhouseService.login(username.get(), password.get());
            loadProperties();
            loadUserBookings();
        } catch (RemoteException e) {
            errorMessage.set("Failed to login: " + e.getMessage());
        }
    }

    public void loadProperties() {
        try {
            List<Property> propertyList = summerhouseService.getAllProperties();
            properties.setAll(propertyList);
        } catch (RemoteException e) {
            errorMessage.set("Failed to load properties: " + e.getMessage());
        }
    }

    public void searchProperties() {
        try {
            List<Property> propertyList = summerhouseService.searchProperties(
                location.get(),
                startDate.get(),
                endDate.get(),
                guests.get()
            );
            properties.setAll(propertyList);
        } catch (RemoteException e) {
            errorMessage.set("Failed to search properties: " + e.getMessage());
        }
    }

    public void loadUserBookings() {
        try {
            List<Booking> bookingList = summerhouseService.getUserBookings(username.get());
            bookings.setAll(bookingList);
        } catch (RemoteException e) {
            errorMessage.set("Failed to load bookings: " + e.getMessage());
        }
    }

    public void createBooking(Property property, LocalDate startDate, LocalDate endDate, int guests) {
        try {
            Booking booking = new Booking(
                java.util.UUID.randomUUID().toString(),
                property.getId().toString(),
                property.getName(),
                username.get(),
                startDate,
                endDate,
                guests,
                property.getPricePerNight() * java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate)
            );
            summerhouseService.createBooking(booking);
            loadUserBookings();
        } catch (RemoteException e) {
            errorMessage.set("Failed to create booking: " + e.getMessage());
        }
    }

    public void cancelBooking(Booking booking) {
        try {
            summerhouseService.cancelBooking(booking.getId());
            loadUserBookings();
        } catch (RemoteException e) {
            errorMessage.set("Failed to cancel booking: " + e.getMessage());
        }
    }

    // Property getters
    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public ObservableList<Property> getProperties() {
        return properties;
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public IntegerProperty guestsProperty() {
        return guests;
    }
} 
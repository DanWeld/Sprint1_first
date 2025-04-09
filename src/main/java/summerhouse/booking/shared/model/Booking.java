package summerhouse.booking.shared.model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.*;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty propertyId = new SimpleStringProperty();
    private final StringProperty propertyName = new SimpleStringProperty();
    private final StringProperty guestUsername = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private final IntegerProperty numberOfGuests = new SimpleIntegerProperty();
    private final DoubleProperty totalPrice = new SimpleDoubleProperty();
    private final ObjectProperty<BookingStatus> status = new SimpleObjectProperty<>(BookingStatus.PENDING);

    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    public Booking(String id, String propertyId, String propertyName, String guestUsername, 
                  LocalDate startDate, LocalDate endDate, 
                  int numberOfGuests, double totalPrice) {
        setId(id);
        setPropertyId(propertyId);
        setPropertyName(propertyName);
        setGuestUsername(guestUsername);
        setStartDate(startDate);
        setEndDate(endDate);
        setNumberOfGuests(numberOfGuests);
        setTotalPrice(totalPrice);
        setStatus(BookingStatus.PENDING);
    }

    // Property getters
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty propertyIdProperty() {
        return propertyId;
    }

    public StringProperty propertyNameProperty() {
        return propertyName;
    }

    public StringProperty guestUsernameProperty() {
        return guestUsername;
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public IntegerProperty numberOfGuestsProperty() {
        return numberOfGuests;
    }

    public DoubleProperty totalPriceProperty() {
        return totalPrice;
    }

    public ObjectProperty<BookingStatus> statusProperty() {
        return status;
    }

    // Getters
    public String getId() {
        return id.get();
    }

    public String getPropertyId() {
        return propertyId.get();
    }

    public String getPropertyName() {
        return propertyName.get();
    }

    public String getGuestUsername() {
        return guestUsername.get();
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public int getNumberOfGuests() {
        return numberOfGuests.get();
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public BookingStatus getStatus() {
        return status.get();
    }

    // Setters
    public void setId(String value) {
        id.set(value);
    }

    public void setPropertyId(String value) {
        propertyId.set(value);
    }

    public void setPropertyName(String value) {
        propertyName.set(value);
    }

    public void setGuestUsername(String value) {
        guestUsername.set(value);
    }

    public void setStartDate(LocalDate value) {
        startDate.set(value);
    }

    public void setEndDate(LocalDate value) {
        endDate.set(value);
    }

    public void setNumberOfGuests(int value) {
        numberOfGuests.set(value);
    }

    public void setTotalPrice(double value) {
        totalPrice.set(value);
    }

    public void setStatus(BookingStatus value) {
        status.set(value);
    }
} 
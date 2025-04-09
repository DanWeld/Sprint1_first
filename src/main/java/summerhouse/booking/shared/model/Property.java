package summerhouse.booking.shared.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.*;

public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final LongProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final IntegerProperty capacity;
    private final DoubleProperty pricePerNight;
    private final ObjectProperty<LocalDate> availableFrom;
    private final StringProperty description;

    // Constructor
    public Property(Long id, String name, String address, int capacity, 
                   double pricePerNight, LocalDate availableFrom, String description) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.pricePerNight = new SimpleDoubleProperty(pricePerNight);
        this.availableFrom = new SimpleObjectProperty<>(availableFrom);
        this.description = new SimpleStringProperty(description);
    }

    // Property getters
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public DoubleProperty pricePerNightProperty() {
        return pricePerNight;
    }

    public ObjectProperty<LocalDate> availableFromProperty() {
        return availableFrom;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    // Value getters
    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public int getCapacity() {
        return capacity.get();
    }

    public double getPricePerNight() {
        return pricePerNight.get();
    }

    public LocalDate getAvailableFrom() {
        return availableFrom.get();
    }

    public String getDescription() {
        return description.get();
    }

    // Value setters
    public void setId(Long id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight.set(pricePerNight);
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom.set(availableFrom);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return capacity.get() == property.capacity.get() &&
                Double.compare(property.pricePerNight.get(), pricePerNight.get()) == 0 &&
                Objects.equals(id.get(), property.id.get()) &&
                Objects.equals(name.get(), property.name.get()) &&
                Objects.equals(address.get(), property.address.get()) &&
                Objects.equals(availableFrom.get(), property.availableFrom.get()) &&
                Objects.equals(description.get(), property.description.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.get(), name.get(), address.get(), capacity.get(), pricePerNight.get(), availableFrom.get(), description.get());
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", capacity=" + getCapacity() +
                ", pricePerNight=" + getPricePerNight() +
                ", availableFrom=" + getAvailableFrom() +
                ", description='" + getDescription() + '\'' +
                '}';
    }
} 
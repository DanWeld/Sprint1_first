import java.util.Date;

public class Booking {
    private User user;
    private Property property;
    private Date startDate;
    private Date endDate;

    public Booking(User user, Property property, Date startDate, Date endDate) {
        this.user = user;
        this.property = property;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public Property getProperty() {
        return property;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isBookingValid() {
        // Logic to check if booking dates are valid
        return property.isAvailable();
    }
}

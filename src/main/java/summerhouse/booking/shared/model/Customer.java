package summerhouse.booking.shared.model;

public class Customer {
    private String name;
    private String bookingStatus;
    private String paymentStatus;

    public Customer(String name, String bookingStatus, String paymentStatus) {
        this.name = name;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
    }

    public String getName() {
        return name;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
} 
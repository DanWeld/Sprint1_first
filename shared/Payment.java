package shared;

public class Payment {
    private Booking booking;
    private double amount;

    public Payment(Booking booking, double amount) {
        this.booking = booking;
        this.amount = amount;
    }

    public Booking getBooking() {
        return booking;
    }

    public double getAmount() {
        return amount;
    }
}

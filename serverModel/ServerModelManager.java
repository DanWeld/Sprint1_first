package serverModel;

import shared.Booking;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerModelManager implements ServerBookingModel {
    private List<Booking> bookings;
    private List<ServerObserver> observers;

    public ServerModelManager() {
        this.bookings = new ArrayList<>();
        this.observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(ServerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ServerObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (ServerObserver observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public boolean bookProperty(String details) {
        // Logic to book property
        notifyObservers("Property booked: " + details);
        return true;
    }

    @Override
    public boolean cancelBooking(String details) {
        // Logic to cancel booking
        notifyObservers("Booking canceled: " + details);
        return true;
    }

    @Override
    public boolean processPayment(String details) {
        // Logic to process payment
        notifyObservers("Payment processed: " + details);
        return true;
    }
}

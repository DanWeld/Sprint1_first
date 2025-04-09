package summerhouse.booking.server;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import summerhouse.booking.shared.model.Booking;
import summerhouse.booking.shared.model.Property;
import summerhouse.booking.shared.model.User;
import summerhouse.booking.shared.network.ISummerhouseService;

public class SummerhouseServiceImpl implements ISummerhouseService {
    private final Map<String, User> users = new HashMap<>();
    private final Map<Long, Property> properties = new HashMap<>();
    private final Map<String, Booking> bookings = new HashMap<>();
    private long nextPropertyId = 1;

    public SummerhouseServiceImpl() {
        // Add some sample data
        addSampleData();
    }

    private void addSampleData() {
        // Add sample properties
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Seaside Villa", "123 Beach Road, Copenhagen", 6, 200.0, LocalDate.now(), "Beautiful seaside villa with ocean view"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Mountain Cabin", "45 Mountain Trail, Aarhus", 4, 150.0, LocalDate.now(), "Cozy cabin in the mountains"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "City Apartment", "78 City Center, Odense", 2, 100.0, LocalDate.now(), "Modern apartment in the city center"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Country House", "12 Country Lane, Aalborg", 8, 250.0, LocalDate.now(), "Spacious house in the countryside"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Beach Bungalow", "9 Beachfront, Esbjerg", 5, 180.0, LocalDate.now(), "Charming bungalow by the beach"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Forest Lodge", "67 Forest Road, Randers", 7, 220.0, LocalDate.now(), "Rustic lodge in the forest"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Lake House", "34 Lakeview, Silkeborg", 6, 210.0, LocalDate.now(), "Serene house by the lake"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Historic Cottage", "56 Old Town, Roskilde", 3, 130.0, LocalDate.now(), "Charming historic cottage"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Luxury Villa", "89 Luxury Lane, Vejle", 10, 500.0, LocalDate.now(), "Luxurious villa with all amenities"));
        properties.put(nextPropertyId++, new Property(nextPropertyId, "Farmhouse", "23 Farm Road, Horsens", 9, 300.0, LocalDate.now(), "Traditional farmhouse with modern comforts"));
    }

    @Override
    public void registerUser(User user) throws RemoteException {
        if (users.containsKey(user.getUsername())) {
            throw new RemoteException("Username already exists");
        }
        users.put(user.getUsername(), user);
    }

    @Override
    public User login(String username, String password) throws RemoteException {
        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RemoteException("Invalid username or password");
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws RemoteException {
        if (!users.containsKey(user.getUsername())) {
            throw new RemoteException("User not found");
        }
        users.put(user.getUsername(), user);
    }

    @Override
    public List<Property> getAllProperties() throws RemoteException {
        return new ArrayList<>(properties.values());
    }

    @Override
    public List<Property> searchProperties(String location, LocalDate startDate, 
                                         LocalDate endDate, int guests) throws RemoteException {
        return properties.values().stream()
            .filter(p -> location == null || location.isEmpty() || 
                        p.getAddress().toLowerCase().contains(location.toLowerCase()))
            .filter(p -> guests <= p.getCapacity())
            .filter(p -> startDate == null || p.getAvailableFrom().isBefore(startDate))
            .collect(Collectors.toList());
    }

    @Override
    public boolean addProperty(Property property) throws RemoteException {
        property.setId(nextPropertyId++);
        properties.put(property.getId(), property);
        return true;
    }

    @Override
    public boolean updateProperty(Property property) throws RemoteException {
        if (!properties.containsKey(property.getId())) {
            return false;
        }
        properties.put(property.getId(), property);
        return true;
    }

    @Override
    public boolean removeProperty(String propertyId) throws RemoteException {
        return properties.remove(Long.parseLong(propertyId)) != null;
    }

    @Override
    public void createBooking(Booking booking) throws RemoteException {
        bookings.put(booking.getId(), booking);
    }

    @Override
    public List<Booking> getUserBookings(String username) throws RemoteException {
        return bookings.values().stream()
            .filter(b -> b.getGuestUsername().equals(username))
            .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getPropertyBookings(String propertyId) throws RemoteException {
        return bookings.values().stream()
            .filter(b -> b.getPropertyId().equals(propertyId))
            .collect(Collectors.toList());
    }

    @Override
    public boolean cancelBooking(String bookingId) throws RemoteException {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            return false;
        }
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        return true;
    }

    @Override
    public void confirmBooking(String bookingId) throws RemoteException {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new RemoteException("Booking not found");
        }
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
    }
} 
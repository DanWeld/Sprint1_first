package summerhouse.booking.shared.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import summerhouse.booking.shared.model.Booking;
import summerhouse.booking.shared.model.Property;
import summerhouse.booking.shared.model.User;

public interface ISummerhouseService extends Remote {
    // User operations
    void registerUser(User user) throws RemoteException;
    User login(String username, String password) throws RemoteException;
    void updateUser(User user) throws RemoteException;
    
    // Property operations
    List<Property> getAllProperties() throws RemoteException;
    List<Property> searchProperties(String location, LocalDate startDate, 
                                  LocalDate endDate, int guests) throws RemoteException;
    boolean addProperty(Property property) throws RemoteException;
    boolean updateProperty(Property property) throws RemoteException;
    boolean removeProperty(String propertyId) throws RemoteException;
    
    // Booking operations
    void createBooking(Booking booking) throws RemoteException;
    List<Booking> getUserBookings(String username) throws RemoteException;
    List<Booking> getPropertyBookings(String propertyId) throws RemoteException;
    boolean cancelBooking(String bookingId) throws RemoteException;
    void confirmBooking(String bookingId) throws RemoteException;
} 
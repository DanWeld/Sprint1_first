public class Admin extends User {
    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    public void addProperty(Property property) {
        // Logic to add a property
    }

    public void removeProperty(Property property) {
        // Logic to remove a property if not booked
    }

    public void updateProperty(Property property) {
        // Logic to update property details
    }
}
